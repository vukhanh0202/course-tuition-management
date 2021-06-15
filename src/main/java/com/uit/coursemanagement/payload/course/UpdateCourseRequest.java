package com.uit.coursemanagement.payload.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ETypeCourse;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UpdateCourseRequest {

    @JsonIgnore
    private Long id;

    private String name;

    private String code;

    @JsonProperty("credit_quantity")
    private Long creditQuantity;

    @JsonProperty("type_course")
    @Enumerated(EnumType.STRING)
    private ETypeCourse typeCourse;

    private String description;

    @JsonProperty("price_basic")
    private Double priceBasic;

    @JsonProperty("price_advanced")
    private Double priceAdvanced;

}
