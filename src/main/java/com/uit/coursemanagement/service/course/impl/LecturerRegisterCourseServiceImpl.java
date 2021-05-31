package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.LecturerRegisterCourseRequest;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IAddNewCourseService;
import com.uit.coursemanagement.service.course.ILecturerRegisterCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LecturerRegisterCourseServiceImpl extends AbstractBaseService<LecturerRegisterCourseRequest, Boolean>
        implements ILecturerRegisterCourseService<LecturerRegisterCourseRequest, Boolean> {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public void preExecute(LecturerRegisterCourseRequest lecturerRegisterCourseRequest) {

    }

    @Override
    public Boolean doing(LecturerRegisterCourseRequest lecturerRegisterCourseRequest) {
        return true;
    }

}
