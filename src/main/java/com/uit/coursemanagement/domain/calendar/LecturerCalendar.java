package com.uit.coursemanagement.domain.calendar;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.lecturer.Lecturer;
import com.uit.coursemanagement.utils.ECalendarShiftConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lecturer_calendar")
public class LecturerCalendar extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Convert(converter = ECalendarShiftConverter.class)
    private List<ECalendarShift> calendarShifts;

    private Long maxQuantityStudent;
}
