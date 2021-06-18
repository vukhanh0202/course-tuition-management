package com.uit.coursemanagement.mapper.lecturer;

import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.lecturer.LecturerDetailDto;
import com.uit.coursemanagement.dto.lecturer.LecturerDto;
import com.uit.coursemanagement.dto.lecturer.LecturerFullDto;
import com.uit.coursemanagement.dto.lecturer.join.CourseSemesterLecturerDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Mapper(componentModel = "spring")
public abstract class LecturerMapper implements MapperBase {

    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private OpenCourseRepository openCourseRepository;

    @BeanMapping(ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toLecturerDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "fullName")
    public abstract LecturerDto toLecturerDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toLecturerDtoList")
    public abstract List<LecturerDto> toLecturerDtoList(List<User> users);

    @Named("toLecturerFullDto")
    @BeforeMapping
    protected void toLecturerFullDto(User user, @MappingTarget LecturerFullDto lecturerFullDto) {
        lecturerFullDto.setTotalCourse((long) user.getLecturer().getOpenCourses().size());
    }

    @BeanMapping(qualifiedByName = "toLecturerFullDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toLecturerFullDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    public abstract LecturerFullDto toLecturerFullDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toLecturerFullDtoList")
    public abstract List<LecturerFullDto> toLecturerFullDtoList(List<User> users);

    @Named("toLecturerDetailDto")
    @BeforeMapping
    protected void toLecturerDetailDto(User user, @MappingTarget LecturerDetailDto lecturerDetailDto) {
        List<OpenCourse> lecturerCourses = openCourseRepository.findAllByLecturerId(user.getId());
        List<CourseSemesterLecturerDto> result = new ArrayList<>();
        Map<Long, List<OpenCourse>> map = new HashMap<>();
        lecturerCourses.forEach(lecturerCourse -> {
            Long semesterId = lecturerCourse.getSemester().getId();
            if (map.containsKey(semesterId)) {
                List<OpenCourse> courses = map.get(semesterId);
                List<OpenCourse> list = new ArrayList<>(Arrays.asList(lecturerCourse));
                list.addAll(courses);
                map.put(semesterId, list);
            } else {
                map.put(semesterId, List.of(lecturerCourse));
            }
        });

        Set<Long> set = map.keySet();
        for (Long key : set) {
            Semester semester = semesterRepository.findById(key).get();
            CourseSemesterLecturerDto item = new CourseSemesterLecturerDto();
            item.setSemesterId(semester.getId());
            item.setSemesterName(semester.getName());
            item.setFromDate(semester.getFromDate());
            item.setToDate(semester.getToDate());
            result.add(item);
        }
        lecturerDetailDto.setList(result);
    }

    @BeanMapping(qualifiedByName = "toLecturerDetailDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    public abstract LecturerDetailDto toLecturerDetailDto(User user);

}
