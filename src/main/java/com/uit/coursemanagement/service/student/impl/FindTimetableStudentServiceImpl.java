package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentTimetableDto;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindTimetableStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindTimetableStudentServiceImpl extends AbstractBaseService<Long, List<StudentTimetableDto>>
        implements IFindTimetableStudentService {

    private final UserRepository userRepository;

    private final UserCourseRepository userCourseRepository;

    private final SemesterRepository semesterRepository;

    private final OpenCourseMapper openCourseMapper;

    @Override
    public void preExecute(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        if (!user.getUserType().equals(EUserType.STUDENT)) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
    }

    @Override
    public List<StudentTimetableDto> doing(Long id) {
        List<StudentTimetableDto> result = new ArrayList<>();
        List<StudentCourse> studentCourses = userCourseRepository.findAllByStudentId(id);

        Map<Long, List<OpenCourse>> map = new HashMap<>();
        studentCourses.forEach(studentCourse -> {
            Long semesterId = studentCourse.getOpenCourse().getSemester().getId();
            if (map.containsKey(semesterId)) {
                List<OpenCourse> courses = map.get(semesterId);
                List<OpenCourse> list = new ArrayList<>(Arrays.asList(studentCourse.getOpenCourse()));
                list.addAll(courses);
                map.put(semesterId, list);
            } else {
                map.put(semesterId, List.of(studentCourse.getOpenCourse()));
            }
        });

        Set<Long> set = map.keySet();
        for (Long key : set) {
            Semester semester = semesterRepository.findById(key).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
            List<OpenCourse> list = map.get(key);

            StudentTimetableDto item = new StudentTimetableDto();
            item.setId(semester.getId());
            item.setName(semester.getName());
            item.setFromDate(semester.getFromDate());
            item.setToDate(semester.getToDate());
            item.setTimetable(openCourseMapper.toOpenCourseRegisterDtoList(list, id));
            result.add(item);
        }
        result.sort((o1, o2) -> {
            // sort DESC
            if (o1.getFromDate().after(o2.getFromDate())) {
                return 1;
            } else if (o2.getFromDate().after(o1.getFromDate())) {
                return -1;
            }
            return 0;
        });
        return result;
    }
}
