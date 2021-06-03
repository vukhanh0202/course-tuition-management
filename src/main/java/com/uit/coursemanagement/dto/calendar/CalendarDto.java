package com.uit.coursemanagement.dto.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarDto {


    private Long id; // id open course

    private String shifts;

    private Date start;

    private Date end;

    private String course;

    @JsonProperty("class_room")
    private String classRoom;

    public CalendarDto(Long id, String shifts, LocalDate start, LocalDate end, String course, String classRoom) {
        this.id = id;
        this.shifts = shifts;
        this.start = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.end = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.course = course;
        this.classRoom = classRoom;
    }
}
