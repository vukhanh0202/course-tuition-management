package com.uit.coursemanagement.dto.lecturer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LecturerFullDto {

    private Long id;

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("total_course")
    private Long totalCourse;

    private String email;
}
