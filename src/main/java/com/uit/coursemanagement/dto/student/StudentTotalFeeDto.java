package com.uit.coursemanagement.dto.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentTotalFeeDto {

    @JsonProperty("credit_quantity")
    private Long creditQuantity;

    @JsonProperty("total_fee")
    private String totalFee;

    @JsonProperty("fee_completed")
    private String feeCompleted;

    @JsonProperty("fee_debt")
    private String feeDebt;

}
