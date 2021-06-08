package com.uit.coursemanagement.mapper.classes;

import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.dto.classes.ClassDetailDto;
import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.payload.classes.UpdateClassRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class ClassMapper implements MapperBase {

    @Autowired
    private OpenCourseMapper openCourseMapper;

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    public abstract ClassRoom toClassRoom(AddClassRequest addClassRequest);

    @BeanMapping(ignoreByDefault = true)
    @Named("toClassDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    public abstract ClassDto toClassDto(ClassRoom classRoom);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toClassDtoList")
    public abstract List<ClassDto> toClassDtoList(List<ClassRoom> classRooms);

    @BeanMapping(ignoreByDefault = true)
    @Named("toClassBasicDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    public abstract ClassDto toClassBasicDto(ClassRoom classRoom);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toClassBasicDtoList")
    public abstract List<ClassDto> toClassBasicDtoList(List<ClassRoom> classRooms);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateClassRoom(UpdateClassRequest dto, @MappingTarget ClassRoom entity);

    @Named("toClassDetailDto")
    @BeforeMapping
    protected void toClassDetailDto(ClassRoom classRoom, @MappingTarget ClassDetailDto classDetailDto) {
        classDetailDto.setCourseList(openCourseMapper.toOpenCourseDtoList(classRoom.getOpenCourses()));
    }

    @BeanMapping(qualifiedByName = "toClassDetailDto", ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    public abstract ClassDetailDto toClassDetailDto(ClassRoom classRoom);

    @BeanMapping(ignoreByDefault = true)
    public abstract List<ClassDetailDto> toClassDetailDtoList(List<ClassRoom> classRooms);
}
