package com.uit.coursemanagement.mapper.course;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class OpenCourseMapper implements MapperBase {

    //*************************************************
    //********** Mapper OpenCourseRequest To OpenCourse **********
    //*************************************************

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "lecturerId", target = "lecturer.id")
    @Mapping(source = "courseId", target = "course.id")
    @Mapping(source = "semesterId", target = "semester.id")
    @Mapping(source = "dayOfWeek", target = "dayOfWeek")
    @Mapping(source = "maxQuantityStudent", target = "maxQuantityStudent")
    @Mapping(source = "shifts", target = "calendarShifts")
    public abstract OpenCourse toOpenCourse(OpenCourseRequest openCourseRequest);


    @Named("toOpenCourseDto")
    @BeforeMapping
    protected void toOpenCourseDto(OpenCourse openCourse, @MappingTarget OpenCourseDto openCourseDto) {
        openCourseDto.setSemester(openCourse.getSemester().toString());
        openCourseDto.setShifts(openCourse.getCalendarShifts().stream().map(ECalendarShift::getValueString).collect(Collectors.toList()));
    }

    @BeanMapping(qualifiedByName = "toOpenCourseDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toOpenCourseDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "lecturer.user.fullName", target = "lecturerName")
    @Mapping(source = "course.name", target = "courseName")
    @Mapping(source = "dayOfWeek", target = "dayOfWeek")
    @Mapping(source = "maxQuantityStudent", target = "maxQuantityStudent")
    public abstract OpenCourseDto toOpenCourseDto(OpenCourse openCourse);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toOpenCourseDtoList")
    public abstract List<OpenCourseDto> toOpenCourseDtoList(List<OpenCourse> courseList);
}
