package com.uit.coursemanagement.payload.lecturer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateLecturerRequest {

    @JsonIgnore
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

}
