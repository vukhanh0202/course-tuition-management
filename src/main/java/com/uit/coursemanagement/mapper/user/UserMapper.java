package com.uit.coursemanagement.mapper.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper implements MapperBase {

    @Named("toStudentUser")
    @BeforeMapping
    protected void toStudentUser(CreateStudentRequest createStudentRequest, @MappingTarget User user) {
        user.setUserType(EUserType.STUDENT);
        user.getStudent().setCode(createStudentRequest.getCode());
        user.getStudent().setDateOfBirth(createStudentRequest.getDateOfBirth());
        user.getStudent().setSchoolYear(createStudentRequest.getSchoolYear());
        user.getStudent().setFaculty(createStudentRequest.getFaculty());
        user.getStudent().setTrainingSystem(createStudentRequest.getTrainingSystem());
        user.getStudent().setTotalCreditQuantity(createStudentRequest.getTotalCreditQuantity());
    }

    @BeanMapping(qualifiedByName = "toStudentUser", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    public abstract User toStudentUser(CreateStudentRequest createStudentRequest);

}
