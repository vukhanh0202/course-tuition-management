package com.uit.coursemanagement.dto.tuition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.dto.response.FileCaption;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuitionPending {

    private Long id;

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("semester_name")
    private String semesterName;

    @JsonProperty("total_fee")
    private String totalFee;

    @JsonProperty("total_fee_payment")
    private String totalFeePayment;
}
