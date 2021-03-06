package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.exception.BadRequestException;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import com.uit.coursemanagement.payload.course.UpdateCourseRequest;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IUpdateCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateCourseServiceImpl extends AbstractBaseService<UpdateCourseRequest, Boolean>
        implements IUpdateCourseService {

    private final CourseMapper courseMapper;

    private final CourseRepository courseRepository;

    @Override
    public void preExecute(UpdateCourseRequest updateCourseRequest) {
        Course course = courseRepository.findById(updateCourseRequest.getId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Course.NOT_FOUND)));
        if (updateCourseRequest.getName() != null && !updateCourseRequest.getName().equals(course.getName())) {
            if (courseRepository.findByName(updateCourseRequest.getName()).isPresent()) {
                throw new InvalidException(messageHelper.getMessage(MessageCode.Course.EXIST, updateCourseRequest.getName()));
            }
        }
        if(courseRepository.findByCode(updateCourseRequest.getCode()).isPresent() && !course.getCode().equals(updateCourseRequest.getCode())){
            throw new BadRequestException(messageHelper.getMessage(MessageCode.Course.EXIST));
        }
    }

    @Override
    public Boolean doing(UpdateCourseRequest updateCourseRequest) {
        Course course = courseRepository.findById(updateCourseRequest.getId()).get();
        courseMapper.updateCourse(updateCourseRequest, course);
        courseRepository.save(course);
        return true;
    }

}
