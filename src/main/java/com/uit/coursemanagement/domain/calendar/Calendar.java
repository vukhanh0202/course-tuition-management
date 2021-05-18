package com.uit.coursemanagement.domain.calendar;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.join.CourseCalendarCreation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "calendar")
public class Calendar extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Enumerated(EnumType.STRING)
    private ECalendarShift calendarShift;

    private Boolean isEmpty = true;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "id")
//    private CourseCalendarCreation courseCalendarCreation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
    private List<CalendarDetail> calendarDetails;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "id", referencedColumnName = "calendar", nullable = false)
//    private CourseCalendarCreation courseCalendarCreation;
}