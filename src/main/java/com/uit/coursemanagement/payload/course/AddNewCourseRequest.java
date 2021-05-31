package com.uit.coursemanagement.payload.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ETypeCourse;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AddNewCourseRequest {

    private String name;

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

    private Long instructor;
}
