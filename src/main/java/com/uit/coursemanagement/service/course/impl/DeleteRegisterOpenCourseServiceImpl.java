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
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.payload.course.RegisterOpenCourseRequest;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IDeleteRegisterOpenCourseService;
import com.uit.coursemanagement.service.course.IRegisterOpenCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeleteRegisterOpenCourseServiceImpl extends AbstractBaseService<RegisterOpenCourseRequest, Boolean>
        implements IDeleteRegisterOpenCourseService<RegisterOpenCourseRequest, Boolean> {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private OpenCourseRepository openCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SemesterRepository semesterRepository;


    @Override
    public void preExecute(RegisterOpenCourseRequest deleteRegisterOpenCourseRequest) {
        User user = userRepository.findById(deleteRegisterOpenCourseRequest.getUserId()).orElse(null);
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
        deleteRegisterOpenCourseRequest.getList().forEach(id ->{
            if (!semesterCourseIdList.contains(id)){
                throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.NOT_FOUND));
            }
        });
        // Kiểm tra user có đăng ký môn học đó hay không
        deleteRegisterOpenCourseRequest.getList().forEach(id ->{
            if (userCourseRepository.findByStudentIdAndOpenCourseId(user.getId(),id).isEmpty()){
                throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.NOT_FOUND));
            }
        });
    }

    @Override
    public Boolean doing(RegisterOpenCourseRequest deleteRegisterOpenCourseRequest) {
        User user = userRepository.findById(deleteRegisterOpenCourseRequest.getUserId()).get();
        List<StudentCourse> list = new ArrayList<>();
        deleteRegisterOpenCourseRequest.getList().forEach(id -> list.add(new StudentCourse(user.getStudent(),
                openCourseRepository.findById(id).get())));
        user.getStudent().removeStudentCourses(list);
        userRepository.save(user);
        return true;
    }

}
