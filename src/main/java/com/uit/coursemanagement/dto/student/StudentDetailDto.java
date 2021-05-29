package com.uit.coursemanagement.dto.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.dto.course.CourseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDetailDto {

    private Long id;

    private String code;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("school_year")
    private String schoolYear;

    @JsonProperty("date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @JsonProperty("credit_quantity_experienced")
    private Long creditQuantityExperienced;

    @JsonProperty("course_experienced_list")
    private List<CourseDto> courseExperiencedList = new ArrayList<>();

    @JsonProperty("credit_quantity_present")
    private Long creditQuantityPresent;

    @JsonProperty("course_present_list")
    private List<CourseDto> coursePresentList = new ArrayList<>();

    @JsonProperty("credit_quantity_debt")
    private Long creditQuantityDebt;

    @JsonProperty("course_debt_list")
    private List<CourseDto> courseDebtList = new ArrayList<>();

    @JsonProperty("total_credit_quantity")
    private Long totalCreditQuantity;

    @JsonProperty("fee_status")
    private String feeStatus;
}
