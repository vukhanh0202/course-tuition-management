package com.uit.coursemanagement.domain.student;

import com.uit.coursemanagement.constant.enums.EFeeStatus;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.join.CourseCalendarCreation;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    private String schoolYear;

    private Long totalCreditQuantity;

    private Long creditQuantityExperienced;

    private Long creditQuantityPresent;

    @Enumerated(EnumType.STRING)
    private EFeeStatus feeStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<CourseCalendarCreation> courseCalendarCreations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentCourse> studentCourses;
}
