package com.uit.coursemanagement.dto.lecturer.join;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseSemesterLecturerDto {

    @JsonProperty("semester_id")
    private Long semesterId;

    @JsonProperty("semester_name")
    private String semesterName;

    @JsonProperty("from_date")
    private Date fromDate;

    @JsonProperty("to_date")
    private Date toDate;

    private List<OpenCourseRegisterDto> list;
}
