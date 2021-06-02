package com.uit.coursemanagement.payload.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddClassRequest {

    private Long id;

    private String name;

    private String description;

}
