package com.uit.coursemanagement.domain.lecturer;

import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.LecturerCalendar;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lecturer")
public class Lecturer extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lecturer")
    private List<LecturerCalendar> lecturerCalendars = new ArrayList<>();
}
