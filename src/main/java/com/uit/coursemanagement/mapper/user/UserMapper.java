package com.uit.coursemanagement.mapper.user;

import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.user.UserFullDto;
import com.uit.coursemanagement.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper  implements MapperBase {

    //*************************************************
    //********** Mapper User To UserFullDto (Search) **********
    //*************************************************

    @BeanMapping(qualifiedByName = "toUserFullDto", ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toUserFullDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "fullName")
    public abstract UserFullDto toUserFullDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toUserFullDtoList")
    public abstract List<UserFullDto> toUserFullDtoList(List<User> users);

}
