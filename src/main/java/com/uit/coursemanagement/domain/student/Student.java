package com.uit.coursemanagement.domain.student;

import com.uit.coursemanagement.constant.enums.EFeeStatus;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String code;

    private Date dateOfBirth;

    private String schoolYear;

    private String faculty;

    private String trainingSystem;

    private Long totalCreditQuantity;

    private Long creditQuantityExperienced;

    private Long creditQuantityPresent;

    private Long creditQuantityDebt;

    private Date startDate;

    private Date endDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<StudentCourse> studentCourses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<TuitionFee> tuitionFees = new ArrayList<>();

    //Constructors, getters and setters removed for brevity
    public void addStudentCourse(StudentCourse studentCourse) {
        if (studentCourses == null) {
            studentCourses = new ArrayList<>();
        }
        studentCourses.add(studentCourse);
        studentCourse.setStudent(this);
    }
    public void addStudentCourses(List<StudentCourse> studentCourses) {
        if (studentCourses == null) {
            studentCourses = new ArrayList<>();
        }
        studentCourses.forEach(this::addStudentCourse);
    }

    public void removeStudentCourse(StudentCourse studentCourse) {
        studentCourses.remove(studentCourse);
        studentCourse.setStudent(null);
    }

    public void removeStudentCourses(List<StudentCourse> studentCourses) {
        studentCourses.forEach(this::removeStudentCourse);
    }
}
