package com.uit.coursemanagement.domain.calendar;

import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.join.CourseCalendarCreation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "calendar_detail")
public class CalendarDetail extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fromTime;

    private String toTime;

    private Long classTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendarDetail")
    private List<CourseCalendarCreation> courseCalendarCreations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;
}
