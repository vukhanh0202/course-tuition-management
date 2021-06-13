package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IDeleteOpenCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteOpenCourseServiceImpl extends AbstractBaseService<Long, Boolean>
        implements IDeleteOpenCourseService<Long, Boolean> {

    private final OpenCourseMapper openCourseMapper;

    private final OpenCourseRepository openCourseRepository;

    public DeleteOpenCourseServiceImpl(OpenCourseMapper openCourseMapper, OpenCourseRepository openCourseRepository) {
        this.openCourseMapper = openCourseMapper;
        this.openCourseRepository = openCourseRepository;
    }

    @Override
    public Boolean doing(Long id) {
        OpenCourse openCourse = openCourseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.OpenCourse.NOT_FOUND)));
        openCourseRepository.delete(openCourse);
        return true;
    }

}
