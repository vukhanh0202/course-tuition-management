package com.uit.coursemanagement.payload.lecturer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CreateLecturerRequest {

    private String username;

    private String password;

    private String email;

    @JsonProperty("full_name")
    private String fullName;
}
