package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IDeleteClassService;
import com.uit.coursemanagement.service.course.IDeleteCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteCourseServiceImpl extends AbstractBaseService<Long, Boolean>
        implements IDeleteCourseService<Long, Boolean> {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void preExecute(Long id) {
        if (courseRepository.findById(id).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Course.NOT_FOUND));
        }
    }

    @Override
    public Boolean doing(Long id) {
        courseRepository.deleteById(id);
        return true;
    }

}
