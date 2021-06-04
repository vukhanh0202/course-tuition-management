package com.uit.coursemanagement.dto.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.dto.calendar.join.CourseClassDto;
import com.uit.coursemanagement.dto.classes.ClassDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShiftDetailDto {


    private ECalendarShift shift;

    @JsonProperty("total_class_room")
    private Integer totalClassRoom;

    private Set<CourseClassDto> courses;


}
