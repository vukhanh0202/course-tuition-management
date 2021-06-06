package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IFindAllOpenCourseCurrentService;
import com.uit.coursemanagement.service.course.IFindAllOpenCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FindAllOpenCourseCurrentServiceImpl extends AbstractBaseService<Void, List<OpenCourseDto>>
        implements IFindAllOpenCourseCurrentService<Void, List<OpenCourseDto>> {

    @Autowired
    private OpenCourseMapper openCourseMapper;

    @Autowired
    private OpenCourseRepository openCourseRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<OpenCourseDto> doing(Void unused) {
        Date date = new Date();
        Semester semester = semesterRepository.findByDate(date)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
        return openCourseMapper.toOpenCourseDtoList(openCourseRepository.findBySemester(semester));
    }

}
