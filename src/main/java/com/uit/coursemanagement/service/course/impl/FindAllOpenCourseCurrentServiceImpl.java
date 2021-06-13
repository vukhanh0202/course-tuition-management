package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IFindAllOpenCourseCurrentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FindAllOpenCourseCurrentServiceImpl extends AbstractBaseService<Long, List<OpenCourseRegisterDto>>
        implements IFindAllOpenCourseCurrentService<Long, List<OpenCourseRegisterDto>> {

    private final OpenCourseMapper openCourseMapper;

    private final OpenCourseRepository openCourseRepository;

    private final SemesterRepository semesterRepository;

    public FindAllOpenCourseCurrentServiceImpl(OpenCourseMapper openCourseMapper, OpenCourseRepository openCourseRepository, SemesterRepository semesterRepository) {
        this.openCourseMapper = openCourseMapper;
        this.openCourseRepository = openCourseRepository;
        this.semesterRepository = semesterRepository;
    }

    @Override
    public List<OpenCourseRegisterDto> doing(Long userId) {
        Date date = new Date();
        Semester semester = semesterRepository.findByDate(date)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
        return openCourseMapper.toOpenCourseRegisterDtoList(openCourseRepository
                .findBySemester(semester), userId);
    }

}
