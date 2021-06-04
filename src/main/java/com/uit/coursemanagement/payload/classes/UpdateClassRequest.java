package com.uit.coursemanagement.payload.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateClassRequest {

    @JsonIgnore
    private Long id;

    private String name;

    private String description;
}
