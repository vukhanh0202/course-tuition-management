package com.uit.coursemanagement.dto.lecturer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.dto.lecturer.join.CourseSemesterLecturerDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LecturerDetailDto {

    private Long id;

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("total_course")
    private Long totalCourse;

    private String email;

    List<CourseSemesterLecturerDto> list;
}
