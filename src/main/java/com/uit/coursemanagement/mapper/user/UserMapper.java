package com.uit.coursemanagement.mapper.user;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.lecturer.Lecturer;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.lecturer.CreateLecturerRequest;
import com.uit.coursemanagement.payload.lecturer.UpdateLecturerRequest;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper implements MapperBase {

    @Named("toStudentUser")
    @BeforeMapping
    protected void toStudentUser(CreateStudentRequest createStudentRequest, @MappingTarget User user) {
        user.setUserType(EUserType.STUDENT);
        user.setStudent(new Student());
        user.getStudent().setUser(user);
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


    @Named("toLecturerUser")
    @BeforeMapping
    protected void toLecturerUser(CreateStudentRequest createLecturerRequest, @MappingTarget User user) {
        user.setUserType(EUserType.LECTURER);
        user.setLecturer(new Lecturer());
        user.getLecturer().setUser(user);
    }

    @BeanMapping(qualifiedByName = "toLecturerUser", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    public abstract User toLecturerUser(CreateLecturerRequest createLecturerRequest);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "fullName", target = "fullName")
    public abstract void updateLecturer(UpdateLecturerRequest dto, @MappingTarget User entity);

}
