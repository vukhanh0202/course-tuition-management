package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.lecturer.join.CourseSemesterLecturerDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.lecturer.IFindCourseRegisterLecturerBySemesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindCourseRegisterLecturerBySemesterServiceImpl extends AbstractBaseService<IFindCourseRegisterLecturerBySemesterService.Input, CourseSemesterLecturerDto>
        implements IFindCourseRegisterLecturerBySemesterService {

    private final UserRepository userRepository;

    private final StudentMapper studentMapper;

    private final UserCourseRepository userCourseRepository;

    private final SemesterRepository semesterRepository;

    private final TuitionFeeRepository tuitionFeeRepository;

    private final OpenCourseMapper openCourseMapper;

    private final OpenCourseRepository openCourseRepository;

    @Override
    public void preExecute(Input input) {
        User user = userRepository.findById(input.getLecturerId()).orElse(null);
        if (user == null) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        if (!user.getUserType().equals(EUserType.LECTURER)) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
    }

    @Override
    public CourseSemesterLecturerDto doing(Input input) {

        List<OpenCourse> lecturerCourses = openCourseRepository
                .findAllByLecturerIdAndSemesterId(input.getLecturerId(), input.getSemesterId());
        Semester semester = semesterRepository.findById(input.getSemesterId()).get();
        CourseSemesterLecturerDto result = new CourseSemesterLecturerDto();
        result.setSemesterId(semester.getId());
        result.setSemesterName(semester.getName());
        result.setFromDate(semester.getFromDate());
        result.setToDate(semester.getToDate());

        result.setList(openCourseMapper.toOpenCourseRegisterDtoList(lecturerCourses, null));
        return result;
    }
}
