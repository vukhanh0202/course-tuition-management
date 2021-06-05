package com.uit.coursemanagement.mapper.course;

import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.classes.UpdateClassRequest;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateCourseRequest;
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
    @Mapping(source = "priceBasic", target = "priceBasic")
    @Mapping(source = "priceAdvanced", target = "priceAdvanced")
    public abstract CourseDto toCourseDto(Course course);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toCourseDtoList")
    public abstract List<CourseDto> toCourseDtoList(List<Course> courseList);


    //*************************************************
    //********** Mapper AddNewCourseRequest To Course (Add New Course) **********
    //*************************************************

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creditQuantity", target = "creditQuantity")
    @Mapping(source = "typeCourse", target = "typeCourse")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "priceBasic", target = "priceBasic")
    @Mapping(source = "priceAdvanced", target = "priceAdvanced")
    public abstract Course toCourse(AddNewCourseRequest addNewCourseRequest);


    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "creditQuantity", target = "creditQuantity")
    @Mapping(source = "typeCourse", target = "typeCourse")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "priceBasic", target = "priceBasic")
    @Mapping(source = "priceAdvanced", target = "priceAdvanced")
    public abstract void updateCourse(UpdateCourseRequest dto, @MappingTarget Course entity);
}
