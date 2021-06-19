package com.uit.coursemanagement.mapper.tuition;

import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.dto.tuition.TuitionPendingDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.utils.ConvertDoubleToString;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class TuitionMapper implements MapperBase {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Named("toTuitionPending")
    @BeforeMapping
    protected void toTuitionPending(TuitionFee tuitionFee, @MappingTarget TuitionPendingDto tuitionPendingDto) {
        List<StudentCourse> studentCourses = userCourseRepository
                .findAllByStudentIdAndOpenCourseSemesterId(tuitionFee.getStudent().getId(),tuitionFee.getSemester().getId());
        tuitionPendingDto.setSemesterName(tuitionFee.getSemester().toString());
        tuitionPendingDto.setTotalFeePayment(ConvertDoubleToString.convert(tuitionFee.getTotalFee()));
        tuitionPendingDto.setTotalFee(ConvertDoubleToString.convert(studentCourses.stream().map(StudentCourse::getOpenCourse).mapToDouble(value -> value.getCourse().getPriceBasic()).sum()));
    }

    @BeanMapping(qualifiedByName = "toTuitionPending", ignoreByDefault = true)
    @Named("toTuitionPendingList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "student.user.username", target = "username")
    @Mapping(source = "student.user.fullName", target = "fullName")
    public abstract TuitionPendingDto toTuitionPending(TuitionFee tuitionFee);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toTuitionPendingList")
    public abstract List<TuitionPendingDto> toTuitionPendingList(List<TuitionFee> semesters);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "name")
    public abstract void updateSemester(UpdateSemesterRequest dto, @MappingTarget Semester entity);

}
