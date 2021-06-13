package com.uit.coursemanagement.payload.semester;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateSemesterRequest {

    @JsonIgnore
    private Long id;

    private String name;

}
