package com.uit.coursemanagement.mapper.calendar;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.dto.calendar.CalendarDetailDto;
import com.uit.coursemanagement.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class CalendarMapper implements MapperBase {

    @Named("toCalendarDetailDto")
    @BeforeMapping
    protected void toCalendarDetailDto(OpenCourse openCourse, @MappingTarget CalendarDetailDto calendarDetailDto) {
//          calendarDetailDto.setStudents(openCourse.get);
        List<ECalendarShift> calendarShifts = openCourse.getCalendarShifts();
        calendarShifts.sort(new Comparator<ECalendarShift>() {
            @Override
            public int compare(ECalendarShift o1, ECalendarShift o2) {
                // sort ASC
                return o1.getTimeStart().compareTo(o2.getTimeStart());
            }
        });
//        openCourse.getCalendarShifts()
        calendarDetailDto.setStart(calendarShifts.get(0).getFromTime());
        calendarDetailDto.setEnd(calendarShifts.get(calendarShifts.size() - 1).getToTime());
    }

    @BeanMapping(qualifiedByName = "toCalendarDetailDto", ignoreByDefault = true)
    @Mapping(source = "course.name", target = "course")
    @Mapping(source = "classRoom.name", target = "classRoom")
    public abstract CalendarDetailDto toCalendarDetailDto(OpenCourse openCourse);

}
