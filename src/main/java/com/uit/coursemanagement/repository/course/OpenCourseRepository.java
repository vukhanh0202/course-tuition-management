package com.uit.coursemanagement.repository.course;

import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.course.OpenCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpenCourseRepository extends JpaRepository<OpenCourse, Long> {

}


