package com.uit.coursemanagement.payload.tuition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentFeeRequest {

    @JsonIgnore
    private Long studentId;

    @JsonProperty("semesterId")
    private Long semesterId;

    private Double totalFee;
}
