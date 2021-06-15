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
public class StudentFeeDto {

    private String semester;

    @JsonProperty("from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;

    @JsonProperty("to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;

    @JsonProperty("credit_quantity")
    private Long creditQuantity;

    @JsonProperty("course_register")
    private String courseRegister;

    @JsonProperty("total_fee")
    private String totalFee;

    @JsonProperty("fee_completed")
    private String feeCompleted;

    @JsonProperty("fee_debt")
    private String feeDebt;

    @JsonProperty("time_completed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCompleted;

    @JsonProperty("fee_status")
    private String feeStatus;
}
