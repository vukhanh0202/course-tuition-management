package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IFindAllCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllCourseServiceImpl extends AbstractBaseService<Void, List<CourseDto>>
        implements IFindAllCourseService<Void, List<CourseDto>> {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDto> doing(Void unused) {
        return courseMapper.toCourseDtoList(courseRepository.findAll());
    }

}