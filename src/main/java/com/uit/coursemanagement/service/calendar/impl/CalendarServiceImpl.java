package com.uit.coursemanagement.service.calendar.impl;

import com.uit.coursemanagement.service.calendar.ICalendarService;
import com.uit.coursemanagement.service.calendar.IFindAllCalendarService;
import com.uit.coursemanagement.service.classes.IAddClassService;
import com.uit.coursemanagement.service.classes.IClassService;
import com.uit.coursemanagement.service.classes.IFindAllClassService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class CalendarServiceImpl implements ICalendarService {

    @Autowired
    IFindAllCalendarService findAllCalendarService;
}
