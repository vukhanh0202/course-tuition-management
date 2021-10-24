package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.StudentConstant;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.payload.course.RegisterOpenCourseRequest;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IRegisterOpenCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterOpenCourseServiceImpl extends AbstractBaseService<RegisterOpenCourseRequest, Boolean>
        implements IRegisterOpenCourseService {

    private final OpenCourseRepository openCourseRepository;

    private final UserRepository userRepository;

    private final SemesterRepository semesterRepository;

    private final UserCourseRepository userCourseRepository;

    @Override
    public void preExecute(RegisterOpenCourseRequest registerOpenCourseRequest) {
        User user = userRepository.findById(registerOpenCourseRequest.getUserId()).orElse(null);
        if (user == null) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        if (!user.getUserType().equals(EUserType.STUDENT)) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
        // Kiểm tra môn có tồn tại trong học kì hiện tại hay không
        Date date = new Date();
        Semester semester = semesterRepository.findByDate(date)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
        List<Long> semesterCourseIdList = semester.getOpenCourses().stream().map(OpenCourse::getId).collect(Collectors.toList());
        registerOpenCourseRequest.getList().forEach(id -> {
            if (!semesterCourseIdList.contains(id)) {
                throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.NOT_FOUND));
            }
        });

        // Kiểm tra student đã đủ tín chỉ trong học kì hiện tại hay chưa
        AtomicReference<Long> totalCredit = new AtomicReference<>(0L);
        registerOpenCourseRequest.getList().forEach(id -> {
            OpenCourse openCourse = openCourseRepository.findById(id).get();
            totalCredit.set(totalCredit.get() + openCourse.getCourse().getCreditQuantity());
        });
        if (user.getStudent().getStudentCourses()
                .stream().filter(studentCourse -> studentCourse.getOpenCourse().getSemester().equals(semester))
                .mapToLong(StudentCourse::getCreditQuantity).sum() + totalCredit.get() > StudentConstant.MAX_SIZE_REGISTER_COURSE) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.Student.FULL_COURSE_IN_SEMESTER));
        }
        // Kiểm tra student đã đăng ký môn này trước đó hay chưa
        List<String> openCourses = user.getStudent().getStudentCourses()
                .stream().map(studentCourse -> studentCourse.getOpenCourse().getCourse().getCode()).collect(Collectors.toList());

        registerOpenCourseRequest.getList().forEach(id -> {
            OpenCourse openCourse = openCourseRepository.findById(id).get();
            if (openCourses.contains(openCourse.getCourse().getCode())) {
                throw new InvalidException(messageHelper.getMessage(MessageCode.Student.EXIST_COURSE_REGISTERED));
            }
            // Kiểm tra môn học đã full sinh viên đăng ký hay chưa
            List<StudentCourse> studentCourseList = userCourseRepository.findAllByOpenCourseId(id);
            if (studentCourseList.size() > 0) {
                StudentCourse item = studentCourseList.get(0);
                if (studentCourseList.size() >= item.getOpenCourse().getMaxQuantityStudent()) {
                    throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.FULL_SIZE));
                }
            }
        });



    }

    @Override
    public Boolean doing(RegisterOpenCourseRequest registerOpenCourseRequest) {
        User user = userRepository.findById(registerOpenCourseRequest.getUserId()).get();
        List<StudentCourse> list = new ArrayList<>();
        registerOpenCourseRequest.getList().forEach(id -> {
            OpenCourse openCourse = openCourseRepository.findById(id).get();
            list.add(new StudentCourse(user.getStudent(),
                    openCourse,
                    openCourse.getCourse().getCreditQuantity(),
                    openCourse.getCourse().getPriceBasic()
            ));
        });
        user.getStudent().addStudentCourses(list);
        userRepository.save(user);
        return true;
    }

}
