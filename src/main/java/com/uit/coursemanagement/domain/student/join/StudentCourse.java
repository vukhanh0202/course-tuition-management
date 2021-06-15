package com.uit.coursemanagement.domain.student.join;

import com.uit.coursemanagement.constant.enums.ECourseStudentStatus;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.student.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student_course")
public class StudentCourse extends SqlEntity {

    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("openCourseId")
    @JoinColumn(name = "open_course_id")
    private OpenCourse openCourse;

    private Long creditQuantity;

    private Double priceBasic;

    @Enumerated(EnumType.STRING)
    private ECourseStudentStatus status = ECourseStudentStatus.PENDING;

    public StudentCourse(Student student, OpenCourse openCourse) {
        this.student = student;
        this.openCourse = openCourse;
    }

    public StudentCourse(Student student, OpenCourse openCourse, Long creditQuantity, Double priceBasic) {
        this.student = student;
        this.openCourse = openCourse;
        this.creditQuantity = creditQuantity;
        this.priceBasic = priceBasic;
    }
}
