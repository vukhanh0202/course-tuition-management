package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.service.course.ICourseService;
import com.uit.coursemanagement.service.course.IFindAllCourseService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private IFindAllCourseService findAllCourseService;

}
