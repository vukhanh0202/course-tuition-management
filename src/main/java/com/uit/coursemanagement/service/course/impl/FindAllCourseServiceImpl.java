package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IFindAllCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllCourseServiceImpl extends AbstractBaseService<String, List<CourseDto>>
        implements IFindAllCourseService {

    private final CourseMapper courseMapper;

    private final CourseRepository courseRepository;

    @Override
    public List<CourseDto> doing(String courseName) {
        return courseMapper.toCourseDtoList(courseRepository.findAllByNameContainingIgnoreCase(courseName));
    }

}
