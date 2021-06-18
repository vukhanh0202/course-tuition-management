package com.uit.coursemanagement.mapper.tuition;

import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.dto.tuition.TuitionPending;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
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
    protected void toTuitionPending(TuitionFee tuitionFee, @MappingTarget TuitionPending tuitionPending) {
        List<StudentCourse> studentCourses = userCourseRepository
                .findAllByStudentIdAndOpenCourseSemesterId(tuitionFee.getStudent().getId(),tuitionFee.getSemester().getId());
        tuitionPending.setSemesterName(tuitionFee.getSemester().toString());
        tuitionPending.setTotalFeePayment(ConvertDoubleToString.convert(tuitionFee.getTotalFee()));
        tuitionPending.setTotalFee(ConvertDoubleToString.convert(studentCourses.stream().map(StudentCourse::getOpenCourse).mapToDouble(value -> value.getCourse().getPriceBasic()).sum()));
    }

    @BeanMapping(qualifiedByName = "toTuitionPending", ignoreByDefault = true)
    @Named("toTuitionPendingList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "student.user.username", target = "username")
    @Mapping(source = "student.user.fullName", target = "fullName")
    public abstract TuitionPending toTuitionPending(TuitionFee tuitionFee);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toTuitionPendingList")
    public abstract List<TuitionPending> toTuitionPendingList(List<TuitionFee> semesters);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "name")
    public abstract void updateSemester(UpdateSemesterRequest dto, @MappingTarget Semester entity);

}
