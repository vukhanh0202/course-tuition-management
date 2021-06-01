package com.uit.coursemanagement.repository.semester;

import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.semester.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

}


