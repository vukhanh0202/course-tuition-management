package com.uit.coursemanagement.service.calendar;

import com.uit.coursemanagement.dto.calendar.CalendarDetailDto;
import com.uit.coursemanagement.dto.calendar.CalendarDto;
import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.service.classes.IAddClassService;
import com.uit.coursemanagement.service.classes.IFindAllClassService;

import java.util.List;

public interface ICalendarService {

    IFindAllCalendarService<IFindAllCalendarService.Input, List<CalendarDto>> getFindAllCalendarService();

    IFindDetailCalendarService<Long, CalendarDetailDto> getFindDetailCalendarService();
}
