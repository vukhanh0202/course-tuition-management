package com.uit.coursemanagement.dto.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.student.join.CourseSemesterStudentDto;
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

    @JsonProperty("total_credit_quantity_experience")
    private Long totalCreditQuantityExperience;

    private String faculty;

    @JsonProperty("training_system")
    private String trainingSystem;

    private List<CourseSemesterStudentDto> list;

    @JsonProperty("total_fee")
    private String totalFee;

    @JsonProperty("fee_completed")
    private String totalFeeCompleted;

    @JsonProperty("fee_debt")
    private String totalFeeDebt;
}
