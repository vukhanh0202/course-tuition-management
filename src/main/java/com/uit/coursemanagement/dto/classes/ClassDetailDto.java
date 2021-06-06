package com.uit.coursemanagement.dto.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassDetailDto {

    private Long id;

    private String name;

    private String description;

    private List<OpenCourseDto> courseList;
}
