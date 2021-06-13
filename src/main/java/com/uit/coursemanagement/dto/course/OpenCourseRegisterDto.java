package com.uit.coursemanagement.dto.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenCourseRegisterDto {

    private Long id;

    @JsonProperty("course_name")
    private String courseName;

    @JsonProperty("credit_quantity")
    private Long creditQuantity;

    @JsonProperty("class_name")
    private String className;

    @JsonProperty("lecturer_name")
    private String lecturerName;

    @JsonProperty("current_quantity_student")
    private Long currentQuantityStudent;

    @JsonProperty("max_quantity_student")
    private Long maxQuantityStudent;

    @JsonProperty("is_disable")
    private Boolean isDisable = false;

}
