package com.uit.coursemanagement.payload.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateStudentRequest {

    @JsonIgnore
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    private String faculty;

    @JsonProperty("training_system")
    private String trainingSystem;

}
