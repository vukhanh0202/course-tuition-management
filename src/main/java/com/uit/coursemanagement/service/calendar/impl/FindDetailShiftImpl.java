package com.uit.coursemanagement.service.calendar.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.dto.calendar.CalendarDetailDto;
import com.uit.coursemanagement.dto.calendar.ShiftDetailDto;
import com.uit.coursemanagement.dto.calendar.join.CourseClassDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.calendar.CalendarMapper;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.calendar.IFindDetailCalendarService;
import com.uit.coursemanagement.service.calendar.IFindDetailShiftService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FindDetailShiftImpl extends AbstractBaseService<IFindDetailShiftService.Input, ShiftDetailDto>
        implements IFindDetailShiftService<IFindDetailShiftService.Input, ShiftDetailDto> {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public ShiftDetailDto doing(Input input) {
        ShiftDetailDto result = new ShiftDetailDto();
        Set<CourseClassDto> courses = new HashSet<>();
        Semester semester = semesterRepository.findByDate(input.getDate()).orElse(null);
        if (semester == null) {
            return null;
        }
//        List<ClassRoom> classRoom = semester.getOpenCourses().stream().filter(openCourse -> {
//            Integer dayOfWeek = input.getDate().toInstant().atZone(ZoneId.systemDefault())
//                    .toLocalDate().getDayOfWeek().getValue();
//            return openCourse.getDayOfWeek().getNumber().equals(dayOfWeek) && openCourse.getCalendarShifts().contains(input.getShift());
//        }).map(OpenCourse::getClassRoom).collect(Collectors.toList());
//        result.setShift(input.getShift());
//        result.setListClass(new HashSet<>(classMapper.toClassDtoList(classRoom)));
//        result.setTotalClassRoom(result.getListClass().size());
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
