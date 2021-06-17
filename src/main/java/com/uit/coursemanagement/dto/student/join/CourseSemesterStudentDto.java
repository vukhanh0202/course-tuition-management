package com.uit.coursemanagement.dto.student.join;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
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
public class CourseSemesterStudentDto {

    @JsonProperty("semester_id")
    private Long semesterId;

    @JsonProperty("semester_name")
    private String semesterName;

    @JsonProperty("from_date")
    private Date fromDate;

    @JsonProperty("to_date")
    private Date toDate;

    @JsonProperty("course_register")
    private List<OpenCourseRegisterDto> list;

    @JsonProperty("credit_quantity")
    private Long creditQuantity;

    @JsonProperty("total_fee")
    private String totalFee;

    @JsonProperty("fee_completed")
    private String feeCompleted;

    @JsonProperty("fee_debt")
    private String feeDebt;

    @JsonIgnore
    private Double totalFeeDouble;

    @JsonIgnore
    private Double feeCompletedDouble;

    @JsonIgnore
    private Double feeDebtDouble;
}
