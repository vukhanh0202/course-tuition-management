package com.uit.coursemanagement.repository.course;

import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    List<Course> findAllByNameContainingIgnoreCase(String name);
}


