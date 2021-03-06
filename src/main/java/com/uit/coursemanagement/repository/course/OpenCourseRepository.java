package com.uit.coursemanagement.repository.course;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.constant.enums.EDayOfWeek;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpenCourseRepository extends JpaRepository<OpenCourse, Long> {

    List<OpenCourse> findBySemester(Semester semester);
    Optional<OpenCourse> findBySemesterAndId(Semester semester, Long courseId);
    List<OpenCourse> findByLecturerUserFullNameContainingOrClassRoomNameContainingOrCourseNameContaining(String fullName,
                                                                                                           String className,
                                                                                                           String courseName);
    List<OpenCourse> findAllByLecturerId(Long lecturerId);

    List<OpenCourse> findAllByLecturerIdAndSemesterId(Long lecturerId, Long semesterId);

    List<OpenCourse> findAllBySemesterIdAndDayOfWeekAndClassRoomId(Long semesterId,
                                                                              EDayOfWeek dayOfWeek, Long classId);

    List<OpenCourse> findAllByLecturerIdAndSemesterIdAndDayOfWeek(Long lectureId,Long semesterId,
                                                                   EDayOfWeek dayOfWeek);

}


