package com.uit.coursemanagement.repository.user;

import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.student.join.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {

    Optional<StudentCourse> findByStudentIdAndOpenCourseId(Long studentId, Long courseId);

    List<StudentCourse> findAllByOpenCourseId(Long courseId);

    List<StudentCourse> findAllByStudentId(Long studentId);

}


