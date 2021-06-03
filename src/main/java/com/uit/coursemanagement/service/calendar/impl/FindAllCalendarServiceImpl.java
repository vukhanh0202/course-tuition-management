package com.uit.coursemanagement.service.calendar.impl;

import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.dto.calendar.CalendarDto;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.calendar.IFindAllCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FindAllCalendarServiceImpl extends AbstractBaseService<IFindAllCalendarService.Input, List<CalendarDto>>
        implements IFindAllCalendarService<IFindAllCalendarService.Input, List<CalendarDto>> {

    @Autowired
    private SemesterRepository semesterRepository;


    @Override
    public List<CalendarDto> doing(Input input) {
        List<CalendarDto> result = new ArrayList<>();
        List<Semester> semesters = semesterRepository.findAllByFromDateAndToDate(input.getFromDate(),input.getToDate());
        LocalDate start = input.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = input.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        semesters.forEach(semester -> {
            List<OpenCourse> openCourses = semester.getOpenCourses();
            openCourses.forEach(openCourse -> {
                LocalDate dateSelect;
                for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                    if (date.getDayOfWeek().getValue() == openCourse.getDayOfWeek().getNumber()) {
                        dateSelect = date;
                        do {
                            LocalDate startTime = dateSelect;
                            LocalDate endTime = dateSelect;
                            String course = openCourse.getCourse().getName();
                            String classRoom = openCourse.getClassRoom().getName();
                            openCourse.getCalendarShifts().forEach(eCalendarShift -> {
                                Date.from(startTime.atStartOfDay(ZoneId.systemDefault()).toInstant()).setTime(eCalendarShift.getTimeStart());
                                Date.from(endTime.atStartOfDay(ZoneId.systemDefault()).toInstant()).setTime(eCalendarShift.getTimeEnd());
                                result.add(new CalendarDto(openCourse.getId(),
                                        eCalendarShift.getValueString(),
                                        startTime,
                                        endTime,
                                        course,
                                        classRoom));
                            });
                            dateSelect = dateSelect.plusDays(7);
                        } while (dateSelect.isBefore(end));
                        break;
                    }
                }
            });
        });
        return result;
    }

}
