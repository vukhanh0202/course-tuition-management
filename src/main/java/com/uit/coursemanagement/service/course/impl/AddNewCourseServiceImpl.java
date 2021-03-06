package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.exception.BadRequestException;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IAddNewCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddNewCourseServiceImpl extends AbstractBaseService<AddNewCourseRequest, Boolean>
        implements IAddNewCourseService {

    private final CourseMapper courseMapper;

    private final CourseRepository courseRepository;

    @Override
    public void preExecute(AddNewCourseRequest addNewCourseRequest) {
        if (courseRepository.findByName(addNewCourseRequest.getName()).isPresent()){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Course.EXIST, addNewCourseRequest.getName()));
        }
        if(courseRepository.findByCode(addNewCourseRequest.getCode()).isPresent()){
            throw new BadRequestException(messageHelper.getMessage(MessageCode.Course.EXIST));
        }
    }

    @Override
    public Boolean doing(AddNewCourseRequest addNewCourseRequest) {
        courseRepository.save(courseMapper.toCourse(addNewCourseRequest));
        return true;
    }

}
