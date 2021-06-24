package com.uit.coursemanagement.dto.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.constant.enums.ETypeCourse;
import com.uit.coursemanagement.domain.lecturer.Lecturer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenCourseDto {

    private Long id;

    @JsonProperty("lecturer_name")
    private String lecturerName;

    private String semester;

    @JsonProperty("course_name")
    private String courseName;

    @JsonProperty("day_of_week")
    private String dayOfWeek;

    @JsonProperty("class_name")
    private String className;

    List<String> shifts;
    List<String> shiftIds;

    @JsonProperty("max_quantity_student")
    private Long maxQuantityStudent;

}
