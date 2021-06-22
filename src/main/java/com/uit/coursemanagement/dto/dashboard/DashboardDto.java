package com.uit.coursemanagement.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardDto {

    @JsonProperty("class_in_day")
    private Integer classInDay;

    @JsonProperty("class_in_week")
    private Integer classInWeek;

    @JsonProperty("student_in_day")
    private Integer studentInDay;

    @JsonProperty("student_in_week")
    private Integer studentInWeek;


}
