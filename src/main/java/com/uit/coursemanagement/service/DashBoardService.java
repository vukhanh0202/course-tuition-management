package com.uit.coursemanagement.service;

import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.dto.calendar.CalendarDto;
import com.uit.coursemanagement.dto.dashboard.DashboardDto;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.calendar.IFindAllCalendarService;
import com.uit.coursemanagement.service.calendar.impl.FindAllCalendarServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DashBoardService {

    @Autowired
    private FindAllCalendarServiceImpl findAllCalendarService;

    @Autowired
    private OpenCourseRepository openCourseRepository;

    @Transactional
    public DashboardDto getInfoDashboard() {
        DashboardDto result = new DashboardDto();
        Integer totalClassDay = 0;
        AtomicReference<Integer> totalStudentDay = new AtomicReference<>(0);

        Integer totalClassWeek = 0;
        AtomicReference<Integer> totalStudentWeek = new AtomicReference<>(0);

        Calendar cal = Calendar.getInstance();
        IFindAllCalendarService.Input input = new IFindAllCalendarService
                .Input(cal.getTime(), cal.getTime(),null,null);
        List<CalendarDto> dto = findAllCalendarService.execute(input);
        totalClassDay = dto.stream().map(CalendarDto::getId).collect(Collectors.toSet()).size();
        dto.forEach(item->{
            OpenCourse openCourse = openCourseRepository.findById(item.getId()).get();
            totalStudentDay.set(totalStudentDay.get() + openCourse.getStudentCourses().size());
        });

        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.DAY_OF_WEEK, 1);
        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.DAY_OF_WEEK, 7);
        IFindAllCalendarService.Input input1 = new IFindAllCalendarService
                .Input(cal3.getTime(), cal4.getTime(),null,null);
        List<CalendarDto> dto2 = findAllCalendarService.execute(input1);
        totalClassWeek = dto2.stream().map(CalendarDto::getId).collect(Collectors.toSet()).size();
        dto2.forEach(item->{
            OpenCourse openCourse = openCourseRepository.findById(item.getId()).get();
            totalStudentWeek.set(totalStudentWeek.get() + openCourse.getStudentCourses().size());
        });
        result.setClassInDay(totalClassDay);
        result.setClassInWeek(totalClassWeek);
        result.setStudentInDay(totalStudentDay.get());
        result.setStudentInWeek(totalStudentWeek.get());
        return result;
    }

}
