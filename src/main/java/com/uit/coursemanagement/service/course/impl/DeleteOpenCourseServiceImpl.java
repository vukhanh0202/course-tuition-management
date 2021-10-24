package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IDeleteOpenCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteOpenCourseServiceImpl extends AbstractBaseService<Long, Boolean>
        implements IDeleteOpenCourseService {

    private final OpenCourseRepository openCourseRepository;

    @Override
    public Boolean doing(Long id) {
        OpenCourse openCourse = openCourseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.OpenCourse.NOT_FOUND)));
        openCourseRepository.delete(openCourse);
        return true;
    }

}
