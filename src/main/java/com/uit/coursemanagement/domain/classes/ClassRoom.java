package com.uit.coursemanagement.domain.classes;

import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.join.CourseCalendarCreation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "class_room")
public class ClassRoom extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classRoom")
    private List<CourseCalendarCreation> courseCalendarCreations;
}
