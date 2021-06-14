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
public class OpenCourseRegisterOfStudentDto {

    private List<OpenCourseRegisterDto> list;

    @JsonProperty("total_credit")
    private Long totalCredit;

    @JsonProperty("total_fee")
    private String totalFee;

}
