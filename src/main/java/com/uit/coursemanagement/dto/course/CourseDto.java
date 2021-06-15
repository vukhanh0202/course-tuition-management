package com.uit.coursemanagement.dto.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ETypeCourse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto {

    private Long id;

    private String name;

    private String code;

    @JsonProperty("credit_quantity")
    private Long creditQuantity;

    @JsonProperty("type_course")
    private ETypeCourse typeCourse;

    @JsonProperty("price_basic")
    private Double priceBasic;

    @JsonProperty("price_advanced")
    private Double priceAdvanced;

    private String instructor;

    private String description;
}
