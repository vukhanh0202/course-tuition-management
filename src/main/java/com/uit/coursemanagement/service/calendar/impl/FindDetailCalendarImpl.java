package com.uit.coursemanagement.service.calendar.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.dto.calendar.CalendarDetailDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.calendar.CalendarMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.calendar.IFindDetailCalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindDetailCalendarImpl extends AbstractBaseService<Long, CalendarDetailDto>
        implements IFindDetailCalendarService {

    private final CalendarMapper calendarMapper;

    private final OpenCourseRepository openCourseRepository;

    @Override
    public void preExecute(Long id) {
        if (openCourseRepository.findById(id).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Course.EXIST));
        }
    }

    @Override
    public CalendarDetailDto doing(Long id) {
        return calendarMapper.toCalendarDetailDto(openCourseRepository.findById(id).get());
    }

}
