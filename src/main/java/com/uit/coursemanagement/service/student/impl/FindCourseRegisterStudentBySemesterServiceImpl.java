package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentDetailDto;
import com.uit.coursemanagement.dto.student.join.CourseSemesterStudentDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindCourseRegisterStudentBySemesterService;
import com.uit.coursemanagement.service.student.IFindDetailStudentService;
import com.uit.coursemanagement.utils.ConvertDoubleToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FindCourseRegisterStudentBySemesterServiceImpl extends AbstractBaseService<IFindCourseRegisterStudentBySemesterService.Input, CourseSemesterStudentDto>
        implements IFindCourseRegisterStudentBySemesterService<IFindCourseRegisterStudentBySemesterService.Input, CourseSemesterStudentDto> {

    private final UserRepository userRepository;

    private final StudentMapper studentMapper;

    private final UserCourseRepository userCourseRepository;

    private final SemesterRepository semesterRepository;

    private final TuitionFeeRepository tuitionFeeRepository;

    private final OpenCourseMapper openCourseMapper;

    public FindCourseRegisterStudentBySemesterServiceImpl(StudentMapper studentMapper, UserCourseRepository userCourseRepository, SemesterRepository semesterRepository, TuitionFeeRepository tuitionFeeRepository, UserRepository userRepository, OpenCourseMapper openCourseMapper) {
        this.studentMapper = studentMapper;
        this.userCourseRepository = userCourseRepository;
        this.semesterRepository = semesterRepository;
        this.tuitionFeeRepository = tuitionFeeRepository;
        this.userRepository = userRepository;
        this.openCourseMapper = openCourseMapper;
    }

    @Override
    public void preExecute(Input input) {
        User user = userRepository.findById(input.getStudentId()).orElse(null);
        if (user == null) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        if (!user.getUserType().equals(EUserType.STUDENT)) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
    }

    @Override
    public CourseSemesterStudentDto doing(Input input) {
        List<StudentCourse> studentCourses = userCourseRepository
                .findAllByStudentIdAndOpenCourseSemesterId(input.getStudentId(), input.getSemesterId());
        List<OpenCourse> list = studentCourses.stream().map(StudentCourse::getOpenCourse).collect(Collectors.toList());
        Semester semester = semesterRepository.findById(input.getSemesterId()).get();
        CourseSemesterStudentDto result = new CourseSemesterStudentDto();
        result.setSemesterId(semester.getId());
        result.setSemesterName(semester.getName());
        result.setFromDate(semester.getFromDate());
        result.setToDate(semester.getToDate());
        result.setCreditQuantity(list.stream().mapToLong(value -> value.getCourse().getCreditQuantity()).sum());

        // FEE
        List<TuitionFee> tuitionFees = tuitionFeeRepository
                .findAllByStudentIdAndSemesterIdAndStatus(input.getStudentId(), semester.getId(), EStatus.COMPLETED);
        // Sort by timeCompleted DESC
        tuitionFees.sort(new Comparator<TuitionFee>() {
            @Override
            public int compare(TuitionFee o1, TuitionFee o2) {
                // sort DESC
                if (o2.getTimeCompleted().after(o1.getTimeCompleted())) {
                    return 1;
                } else if (o1.getTimeCompleted().after(o2.getTimeCompleted())) {
                    return -1;
                }
                return 0;
            }
        });
        Double totalFee = list.stream().mapToDouble(value -> value.getCourse().getPriceBasic()).sum();
        Double completedFee = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();
        result.setTotalFee(ConvertDoubleToString.convert(totalFee));
        result.setFeeCompleted(ConvertDoubleToString.convert(completedFee));
        result.setFeeDebt(ConvertDoubleToString.convert(totalFee - completedFee));
        result.setList(openCourseMapper.toOpenCourseRegisterDtoList(list, input.getStudentId()));
        result.setTotalFeeDouble(totalFee);
        result.setFeeCompletedDouble(completedFee);
        result.setFeeDebtDouble(totalFee - completedFee);

        return result;
    }
}
