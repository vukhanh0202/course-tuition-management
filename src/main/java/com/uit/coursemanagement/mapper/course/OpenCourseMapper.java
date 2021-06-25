package com.uit.coursemanagement.mapper.course;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
import com.uit.coursemanagement.dto.tuition.TuitionPendingDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateOpenCourseRequest;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.utils.ConvertDoubleToString;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class OpenCourseMapper implements MapperBase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ClassRepository classRepository;

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

    @Named("updateOpenCourseBefore")
    @BeforeMapping
    protected void updateOpenCourseBefore(UpdateOpenCourseRequest dto, @MappingTarget OpenCourse entity) {
        if (dto.getLecturerId() != null) {
            entity.setLecturer(userRepository.findById(dto.getLecturerId()).get().getLecturer());
        }
        if (dto.getSemesterId() != null) {
            entity.setSemester(semesterRepository.findById(dto.getSemesterId()).get());
        }
        if (dto.getCourseId() != null) {
            entity.setCourse(courseRepository.findById(dto.getCourseId()).get());
        }
        if (dto.getClassId() != null) {
            entity.setClassRoom(classRepository.findById(dto.getClassId()).get());
        }
    }

    @BeanMapping(qualifiedByName = "updateOpenCourseBefore", ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "dayOfWeek", target = "dayOfWeek")
    @Mapping(source = "maxQuantityStudent", target = "maxQuantityStudent")
    @Mapping(source = "shifts", target = "calendarShifts")
    public abstract void updateOpenCourse(UpdateOpenCourseRequest dto, @MappingTarget OpenCourse entity);


    @Named("toOpenCourseDto")
    @BeforeMapping
    protected void toOpenCourseDto(OpenCourse openCourse, @MappingTarget OpenCourseDto openCourseDto) {
        openCourseDto.setSemester(openCourse.getSemester().toString());
        openCourseDto.setShifts(openCourse.getCalendarShifts().stream().map(ECalendarShift::getValueString).collect(Collectors.toList()));
        openCourseDto.setShiftIds(openCourse.getCalendarShifts().stream().map(ECalendarShift::name).collect(Collectors.toList()));
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
        if (userId != null) {
            if (openCourse.getStudentCourses().stream().filter(studentCourse -> studentCourse.getStudent().getId().equals(userId)).count() > 0) {
                openCourseRegisterDto.setIsDisable(true);
            } else {
                openCourseRegisterDto.setIsDisable(false);
            }
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
