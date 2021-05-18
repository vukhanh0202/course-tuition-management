package com.uit.coursemanagement.mapper.course;

import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class CourseMapper implements MapperBase {


    //*************************************************
    //********** Mapper Course To CourseDto (Search) **********
    //*************************************************

    @BeanMapping(qualifiedByName = "toCourseDto", ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toCourseDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creditQuantity", target = "creditQuantity")
    @Mapping(source = "typeCourse", target = "typeCourse")
    public abstract CourseDto toCourseDto(Course course);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toCourseDtoList")
    public abstract List<CourseDto> toCourseDtoList(List<Course> courseList);


}
