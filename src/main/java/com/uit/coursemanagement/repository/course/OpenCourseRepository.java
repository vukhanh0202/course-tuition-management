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

    List<OpenCourse> findBySemesterAndLecturerUserFullNameContainingAndClassRoomNameContainingAndCourseNameContaining(Semester semester,
                                                                                                                      String fullName,
                                                                                                                      String className,
                                                                                                                      String courseName);

    List<OpenCourse> findByLecturerUserFullNameContainingAndClassRoomNameContainingAndCourseNameContaining(String fullName,
                                                                                                           String className,
                                                                                                           String courseName);
}


