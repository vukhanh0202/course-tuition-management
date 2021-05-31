package com.uit.coursemanagement.dto.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private Long id;

    private String code;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    @JsonProperty("date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @JsonProperty("school_year")
    private String schoolYear;

    @JsonProperty("credit_quantity_experienced")
    private String creditQuantityExperienced;

    @JsonProperty("credit_quantity_present")
    private Long creditQuantityPresent;

    @JsonProperty("fee_status")
    private String feeStatus;
}
