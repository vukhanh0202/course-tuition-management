package com.uit.coursemanagement.domain.student.join;

import com.uit.coursemanagement.constant.enums.ECourseStudentStatus;
import com.uit.coursemanagement.constant.enums.EFeeStatus;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.CalendarDetail;
import com.uit.coursemanagement.domain.calendar.join.CourseCalendarCreation;
import com.uit.coursemanagement.domain.calendar.join.CourseCalendarCreationId;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private ECourseStudentStatus status;
}
