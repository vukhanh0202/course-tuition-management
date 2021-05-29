package com.uit.coursemanagement.mapper.student;

import com.uit.coursemanagement.constant.enums.ECourseStudentStatus;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentDetailDto;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class StudentMapper implements MapperBase {

    @Autowired
    private CourseMapper courseMapper;

    //*************************************************
    //********** Mapper User To UserStudentDto (Search) **********
    //*************************************************
    @Named("toStudentDto")
    @BeforeMapping
    protected void toStudentDto(User user, @MappingTarget StudentDto studentDto) {
        studentDto.setCreditQuantityExperienced(user.getStudent().getCreditQuantityExperienced()
                + "/" + user.getStudent().getTotalCreditQuantity());
    }

    @BeanMapping(qualifiedByName = "toStudentDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toStudentDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "student.code", target = "code")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "student.schoolYear", target = "schoolYear")
    @Mapping(source = "student.creditQuantityPresent", target = "creditQuantityPresent")
    @Mapping(source = "student.feeStatus", target = "feeStatus")
    public abstract StudentDto toStudentDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toStudentDtoList")
    public abstract List<StudentDto> toStudentDtoList(List<User> users);

    //*************************************************
    //********** Mapper User To UserStudentDto (Search) **********
    //*************************************************
    @Named("toStudentDetailDto")
    @BeforeMapping
    protected void toStudentDetailDto(User user, @MappingTarget StudentDetailDto studentDetailDto) {
        List<StudentCourse> courseList = user.getStudent().getStudentCourses();

        studentDetailDto.setCourseExperiencedList(courseMapper.toCourseDtoList(courseList.stream()
                .filter(studentCourse -> studentCourse.getStatus() == ECourseStudentStatus.COMPLETED)
                .map(StudentCourse::getCourse).collect(Collectors.toList())));
        studentDetailDto.setCoursePresentList(courseMapper.toCourseDtoList(courseList.stream()
                .filter(studentCourse -> studentCourse.getStatus() == ECourseStudentStatus.PENDING)
                .map(StudentCourse::getCourse).collect(Collectors.toList())));
        studentDetailDto.setCourseDebtList(courseMapper.toCourseDtoList(courseList.stream()
                .filter(studentCourse -> studentCourse.getStatus() == ECourseStudentStatus.DEBT)
                .map(StudentCourse::getCourse).collect(Collectors.toList())));
    }

    @BeanMapping(qualifiedByName = "toStudentDetailDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toStudentDetailDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "student.code", target = "code")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "student.schoolYear", target = "schoolYear")
    @Mapping(source = "student.creditQuantityPresent", target = "creditQuantityPresent")
    @Mapping(source = "student.creditQuantityExperienced", target = "creditQuantityExperienced")
    @Mapping(source = "student.totalCreditQuantity", target = "totalCreditQuantity")
    @Mapping(source = "student.feeStatus", target = "feeStatus")
    public abstract StudentDetailDto toStudentDetailDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toStudentDetailDtoList")
    public abstract List<StudentDetailDto> toStudentDetailDtoList(List<User> users);
}
