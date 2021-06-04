package com.uit.coursemanagement.dto.calendar.join;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.dto.classes.ClassDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseClassDto {


    @JsonProperty("id_course")
    private Long idCourse;

    @JsonProperty("class_room")
    private String classRoom;

    public CourseClassDto(Long idCourse, String classRoom) {
        this.idCourse = idCourse;
        this.classRoom = classRoom;
    }
}
