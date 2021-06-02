package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IFindAllCourseService;
import com.uit.coursemanagement.service.course.IFindAllOpenCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllOpenCourseServiceImpl extends AbstractBaseService<Void, List<OpenCourseDto>>
        implements IFindAllOpenCourseService<Void, List<OpenCourseDto>> {

    @Autowired
    private OpenCourseMapper openCourseMapper;

    @Autowired
    private OpenCourseRepository openCourseRepository;

    @Override
    public List<OpenCourseDto> doing(Void unused) {
        return openCourseMapper.toOpenCourseDtoList(openCourseRepository.findAll());
    }

}
