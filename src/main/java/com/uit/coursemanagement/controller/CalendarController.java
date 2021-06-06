package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.service.calendar.ICalendarService;
import com.uit.coursemanagement.service.calendar.IFindAllCalendarService;
import com.uit.coursemanagement.service.calendar.IFindDetailShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@Slf4j
@RestController
@Api(value = "Calendar APIs")
public class CalendarController {

    @Autowired
    private ICalendarService calendarService;

    @ApiOperation(value = "Search calendar", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/calendar/search")
    public ResponseEntity<?> findAllCalendar(@ApiParam(value = "Example", defaultValue = "2021-10-02T00:00:00.00Z")
                                             @RequestParam(value = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fromDate,
                                             @ApiParam(value = "Example", defaultValue = "2021-10-30T00:00:00.00Z")
                                             @RequestParam(value = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date toDate,
                                             @RequestParam(value = "class_name", required = false) String className,
                                             @RequestParam(value = "course_name", required = false) String courseName) {
        IFindAllCalendarService.Input input = new IFindAllCalendarService.Input(fromDate, toDate, className, courseName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(calendarService.getFindAllCalendarService().execute(input)));
    }

    @ApiOperation(value = "Detail calendar", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/calendar/{id}")
    public ResponseEntity<?> findDetailCalendar(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(calendarService.getFindDetailCalendarService().execute(id)));
    }

    @ApiOperation(value = "Get detail shift", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/calendar/shift")
    public ResponseEntity<?> getDetailShift(@ApiParam(value = "Example", defaultValue = "2021-10-02T00:00:00.00Z")
                                            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date,
                                            @RequestParam(value = "shift") ECalendarShift shift) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(calendarService.getFindDetailShiftService()
                        .execute(new IFindDetailShiftService.Input(date, shift))));
    }
}
