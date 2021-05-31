package com.uit.coursemanagement.domain.course;

import com.uit.coursemanagement.constant.enums.ETypeCourse;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long creditQuantity;

    @Enumerated(EnumType.STRING)
    private ETypeCourse typeCourse;

    private String description;

    private Double priceBasic;

    private Double priceAdvanced;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
//    private List<CourseCalendarCreation> courseCalendarCreations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<StudentCourse> studentCourses;
}
