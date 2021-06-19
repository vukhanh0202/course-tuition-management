package com.uit.coursemanagement.dto.tuition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuitionDto {

    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("total_credit_in_semester")
    private Long totalCreditInSemester;

    @JsonProperty("total_fee_in_semester")
    private String totalFeeInSemester;

    @JsonProperty("total_fee_payment")
    private String totalFeePayment;

    @JsonProperty("total_fee_debt")
    private String totalFeeDebt;

    @JsonProperty("is_completed")
    private Boolean isCompleted;
}
