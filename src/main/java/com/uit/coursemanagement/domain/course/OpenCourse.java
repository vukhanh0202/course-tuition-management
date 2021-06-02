package com.uit.coursemanagement.domain.course;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.constant.enums.EDayOfWeek;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.lecturer.Lecturer;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.utils.ECalendarShiftConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "open_course")
public class OpenCourse extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    @Enumerated(EnumType.STRING)
    private EDayOfWeek dayOfWeek;

    @Convert(converter = ECalendarShiftConverter.class)
    private List<ECalendarShift> calendarShifts;

    private Long maxQuantityStudent;
}
