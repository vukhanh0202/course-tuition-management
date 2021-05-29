package com.uit.coursemanagement.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStudentDto {

    private Long id;

    private String code;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("school_year")
    private String schoolYear;

    @JsonProperty("credit_quantity_experienced")
    private String creditQuantityExperienced;

    @JsonProperty("credit_quantity_present")
    private Long creditQuantityPresent;

    @JsonProperty("fee_status")
    private String feeStatus;
}
