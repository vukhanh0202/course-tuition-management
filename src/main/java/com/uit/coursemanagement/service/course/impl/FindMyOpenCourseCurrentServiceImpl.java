package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterOfStudentDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IFindAllOpenCourseCurrentService;
import com.uit.coursemanagement.service.course.IFindMyOpenCourseCurrentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FindMyOpenCourseCurrentServiceImpl extends AbstractBaseService<Long, OpenCourseRegisterOfStudentDto>
        implements IFindMyOpenCourseCurrentService<Long, OpenCourseRegisterOfStudentDto> {

    private final OpenCourseMapper openCourseMapper;

    private final SemesterRepository semesterRepository;

    private final UserRepository userRepository;

    public FindMyOpenCourseCurrentServiceImpl(OpenCourseMapper openCourseMapper, SemesterRepository semesterRepository, UserRepository userRepository) {
        this.openCourseMapper = openCourseMapper;
        this.semesterRepository = semesterRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OpenCourseRegisterOfStudentDto doing(Long userId) {
        Student student = userRepository.findById(userId).get().getStudent();
        Date date = new Date();
        Semester semester = semesterRepository.findByDate(date)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
        OpenCourseRegisterOfStudentDto result = new OpenCourseRegisterOfStudentDto();
        result.setList(openCourseMapper.toOpenCourseRegisterDtoList(student.getStudentCourses()
                .stream()
                .map(StudentCourse::getOpenCourse)
                .filter(openCourse -> openCourse.getSemester().equals(semester))
                .collect(Collectors.toList()), userId));
        result.setTotalCredit(result.getList().stream().mapToLong(OpenCourseRegisterDto::getCreditQuantity)
                .sum());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        result.setTotalFee(formatter.format(5000000)+" VNĐ");
        return result;
    }

}
