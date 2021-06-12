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
public class FindAllOpenCourseServiceImpl extends AbstractBaseService<String, List<OpenCourseDto>>
        implements IFindAllOpenCourseService<String, List<OpenCourseDto>> {

    private final OpenCourseMapper openCourseMapper;

    private final OpenCourseRepository openCourseRepository;

    public FindAllOpenCourseServiceImpl(OpenCourseMapper openCourseMapper, OpenCourseRepository openCourseRepository) {
        this.openCourseMapper = openCourseMapper;
        this.openCourseRepository = openCourseRepository;
    }

    @Override
    public List<OpenCourseDto> doing(String search) {
        return openCourseMapper.toOpenCourseDtoList(openCourseRepository
                .findByLecturerUserFullNameAndClassRoomNameAndCourseName(search, search, search));
    }

}
