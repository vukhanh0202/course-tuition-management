package com.uit.coursemanagement.mapper.semester;

import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateCourseRequest;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;
import org.mapstruct.*;
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
    @Named("toSemesterDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    @Mapping(source = "status", target = "status")
    public abstract SemesterDto toSemesterDto(Semester semester);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toSemesterDtoList")
    public abstract List<SemesterDto> toSemesterDtoList(List<Semester> semesters);

    @Named("toSemesterBasicDto")
    @BeforeMapping
    protected void toSemesterBasicDto(Semester semester, @MappingTarget SemesterDto semesterDto) {
        semesterDto.setName(semester.toString());
    }

    @BeanMapping(qualifiedByName = "toSemesterBasicDto", ignoreByDefault = true)
    @Named("toSemesterBasicDtoList")
    @Mapping(source = "id", target = "id")
    public abstract SemesterDto toSemesterBasicDto(Semester semester);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toSemesterBasicDtoList")
    public abstract List<SemesterDto> toSemesterBasicDtoList(List<Semester> semesters);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    public abstract void updateSemester(UpdateSemesterRequest dto, @MappingTarget Semester entity);


}
