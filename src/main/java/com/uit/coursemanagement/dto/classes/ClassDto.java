package com.uit.coursemanagement.dto.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.EStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassDto {

    private Long id;

    private String name;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDto classDto = (ClassDto) o;
        return Objects.equals(id, classDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
