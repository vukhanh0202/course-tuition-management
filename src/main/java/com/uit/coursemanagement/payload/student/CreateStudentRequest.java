package com.uit.coursemanagement.payload.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CreateStudentRequest {



    private String username;

    private String password;

    private String email;

    @JsonProperty("full_name")
    private String fullName;

    private String code;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    private String schoolYear;

    private String faculty;

    private String trainingSystem;

    private Long totalCreditQuantity;
}
