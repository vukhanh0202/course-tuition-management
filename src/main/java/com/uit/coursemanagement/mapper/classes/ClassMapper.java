package com.uit.coursemanagement.mapper.classes;

import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class ClassMapper implements MapperBase {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    public abstract ClassRoom toClassRoom(AddClassRequest addClassRequest);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    public abstract ClassDto toClassDto(ClassRoom classRoom);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<ClassDto> toClassDtoList(List<ClassRoom> classRooms);

}
