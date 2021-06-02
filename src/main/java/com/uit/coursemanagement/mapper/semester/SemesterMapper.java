package com.uit.coursemanagement.mapper.semester;

import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class SemesterMapper implements MapperBase {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    public abstract Semester toSemester(AddSemesterRequest addSemesterRequest);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    @Mapping(source = "status", target = "status")
    public abstract SemesterDto toSemesterDto(Semester semester);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<SemesterDto> toSemesterDtoList(List<Semester> semesters);

}
