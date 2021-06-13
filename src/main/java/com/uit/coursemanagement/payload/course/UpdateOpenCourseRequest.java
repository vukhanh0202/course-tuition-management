package com.uit.coursemanagement.payload.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.constant.enums.EDayOfWeek;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
public class UpdateOpenCourseRequest {

    @JsonIgnore
    private Long id;

    @JsonProperty("lecturer_id")
    private Long lecturerId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("semester_id")
    private Long semesterId;

    @JsonProperty("day_of_week")
    @Enumerated(EnumType.STRING)
    private EDayOfWeek dayOfWeek;

    @JsonProperty("class_id")
    private Long classId;

    private List<ECalendarShift> shifts;

    @JsonProperty("max_quantity_student")
    private Long maxQuantityStudent;
}
