package com.uit.coursemanagement.mapper.course;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateOpenCourseRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class OpenCourseMapper implements MapperBase {

    //*************************************************
    //********** Mapper OpenCourseRequest To OpenCourse **********
    //*************************************************

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "lecturerId", target = "lecturer.id")
    @Mapping(source = "courseId", target = "course.id")
    @Mapping(source = "semesterId", target = "semester.id")
    @Mapping(source = "dayOfWeek", target = "dayOfWeek")
    @Mapping(source = "maxQuantityStudent", target = "maxQuantityStudent")
    @Mapping(source = "shifts", target = "calendarShifts")
    @Mapping(source = "classId", target = "classRoom.id")
    public abstract OpenCourse toOpenCourse(OpenCourseRequest openCourseRequest);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lecturerId", target = "lecturer.id")
    @Mapping(source = "courseId", target = "course.id")
    @Mapping(source = "semesterId", target = "semester.id")
    @Mapping(source = "dayOfWeek", target = "dayOfWeek")
    @Mapping(source = "maxQuantityStudent", target = "maxQuantityStudent")
    @Mapping(source = "shifts", target = "calendarShifts")
    @Mapping(source = "classId", target = "classRoom.id")
    public abstract void updateOpenCourse(UpdateOpenCourseRequest dto, @MappingTarget OpenCourse entity);


    @Named("toOpenCourseDto")
    @BeforeMapping
    protected void toOpenCourseDto(OpenCourse openCourse, @MappingTarget OpenCourseDto openCourseDto) {
        openCourseDto.setSemester(openCourse.getSemester().toString());
        openCourseDto.setShifts(openCourse.getCalendarShifts().stream().map(ECalendarShift::getValueString).collect(Collectors.toList()));
    }

    @BeanMapping(qualifiedByName = "toOpenCourseDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toOpenCourseDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "lecturer.user.fullName", target = "lecturerName")
    @Mapping(source = "course.name", target = "courseName")
    @Mapping(source = "dayOfWeek", target = "dayOfWeek")
    @Mapping(source = "maxQuantityStudent", target = "maxQuantityStudent")
    @Mapping(source = "classRoom.name", target = "className")
    public abstract OpenCourseDto toOpenCourseDto(OpenCourse openCourse);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toOpenCourseDtoList")
    public abstract List<OpenCourseDto> toOpenCourseDtoList(List<OpenCourse> courseList);

    @Named("toOpenCourseRegisterDto")
    @BeforeMapping
    protected void toOpenCourseRegisterDto(OpenCourse openCourse,
                                           @MappingTarget OpenCourseRegisterDto openCourseRegisterDto, @Context Long userId) {
        AtomicReference<String> lessons = new AtomicReference<>("Tiết ");
        openCourse.getCalendarShifts().forEach(eCalendarShift -> {
            lessons.updateAndGet(v -> v + eCalendarShift.getLesson());
        });
        openCourseRegisterDto.setClassName(openCourse.getDayOfWeek().getValue() +
                ", " + lessons + ", " + openCourse.getClassRoom().getName());
        openCourseRegisterDto.setCurrentQuantityStudent((long) openCourse.getStudentCourses().size());
        if (openCourse.getStudentCourses().stream().map(studentCourse -> studentCourse.getStudent().getId().equals(userId)).count()>0){
            openCourseRegisterDto.setIsDisable(true);
        }else{
            openCourseRegisterDto.setIsDisable(false);
        }
    }

    @BeanMapping(qualifiedByName = "toOpenCourseRegisterDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toOpenCourseRegisterDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "lecturer.user.fullName", target = "lecturerName")
    @Mapping(source = "course.creditQuantity", target = "creditQuantity")
    @Mapping(source = "course.name", target = "courseName")
    @Mapping(source = "maxQuantityStudent", target = "maxQuantityStudent")
    public abstract OpenCourseRegisterDto toOpenCourseRegisterDto(OpenCourse openCourse, @Context Long userId);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toOpenCourseRegisterDtoList")
    public abstract List<OpenCourseRegisterDto> toOpenCourseRegisterDtoList(List<OpenCourse> courseList, @Context Long userId);
}
