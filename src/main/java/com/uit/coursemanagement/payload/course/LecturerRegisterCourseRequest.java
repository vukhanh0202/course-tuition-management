package com.uit.coursemanagement.payload.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ECalendarShift;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LecturerRegisterCourseRequest {

    @JsonProperty("lecturer_id")
    private Long lecturerId;

    private Date date;

    private List<ECalendarShift> shifts;

    @JsonProperty("max_quantity_student")
    private Long maxQuantityStudent;
}
