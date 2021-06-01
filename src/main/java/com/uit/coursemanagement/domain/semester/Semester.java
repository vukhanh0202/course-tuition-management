package com.uit.coursemanagement.domain.semester;

import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.Calendar;
import com.uit.coursemanagement.domain.course.OpenCourse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "semester")
public class Semester extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Date fromDate;

    private Date toDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "semester")
    private List<OpenCourse> openCourses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "semester")
    private List<Calendar> calendars = new ArrayList<>();
}
