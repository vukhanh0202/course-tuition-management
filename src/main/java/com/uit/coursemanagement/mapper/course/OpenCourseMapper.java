package com.uit.coursemanagement.mapper.course;

import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

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

}
