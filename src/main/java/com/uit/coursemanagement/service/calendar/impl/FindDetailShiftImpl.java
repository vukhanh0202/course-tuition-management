package com.uit.coursemanagement.service.calendar.impl;

import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.dto.calendar.ShiftDetailDto;
import com.uit.coursemanagement.dto.calendar.join.CourseClassDto;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.calendar.IFindDetailShiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindDetailShiftImpl extends AbstractBaseService<IFindDetailShiftService.Input, ShiftDetailDto>
        implements IFindDetailShiftService {

    private final SemesterRepository semesterRepository;

    @Override
    public ShiftDetailDto doing(Input input) {
        ShiftDetailDto result = new ShiftDetailDto();
        Set<CourseClassDto> courses = new HashSet<>();
        Semester semester = semesterRepository.findByDate(input.getDate()).orElse(null);
        if (semester == null) {
            return null;
        }
        semester.getOpenCourses().forEach(openCourse -> {
            Integer dayOfWeek = input.getDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate().getDayOfWeek().getValue();
            if (openCourse.getDayOfWeek().getNumber().equals(dayOfWeek) && openCourse.getCalendarShifts().contains(input.getShift())){
                courses.add(new CourseClassDto(openCourse.getId(), openCourse.getClassRoom().getName()));
            }
            return ;

        });
        result.setShift(input.getShift());
        result.setCourses(courses);
        result.setTotalClassRoom(result.getCourses().size());
        return result;
    }

}
