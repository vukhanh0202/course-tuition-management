package com.uit.coursemanagement.mapper.user;

import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.user.UserStudentDto;
import com.uit.coursemanagement.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper implements MapperBase {

    //*************************************************
    //********** Mapper User To UserStudentDto (Search) **********
    //*************************************************
    @Named("toUserStudentDto")
    @BeforeMapping
    protected void toUserStudentDto(User user, @MappingTarget UserStudentDto userStudentDto) {
        userStudentDto.setCreditQuantityExperienced(user.getStudent().getCreditQuantityExperienced()
                + "/" + user.getStudent().getTotalCreditQuantity());
    }

    @BeanMapping(qualifiedByName = "toUserStudentDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toUserStudentDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "student.code", target = "code")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "student.schoolYear", target = "schoolYear")
    @Mapping(source = "student.creditQuantityPresent", target = "creditQuantityPresent")
    @Mapping(source = "student.feeStatus", target = "feeStatus")
    public abstract UserStudentDto toUserStudentDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toUserStudentDtoList")
    public abstract List<UserStudentDto> toUserStudentDtoList(List<User> users);

}
