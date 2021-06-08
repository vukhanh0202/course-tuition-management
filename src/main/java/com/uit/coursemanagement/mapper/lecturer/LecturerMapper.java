package com.uit.coursemanagement.mapper.lecturer;

import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.lecturer.LecturerDto;
import com.uit.coursemanagement.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class LecturerMapper implements MapperBase {

    @BeanMapping(ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toLecturerDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "fullName")
    public abstract LecturerDto toLecturerDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toLecturerDtoList")
    public abstract List<LecturerDto> toLecturerDtoList(List<User> users);
}
