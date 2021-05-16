package com.uit.coursemanagement.domain.calendar.join;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class CourseCalendarCreationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long calendarDetailId;
    private Long classRoomId;
    private Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCalendarCreationId that = (CourseCalendarCreationId) o;
        return Objects.equals(calendarDetailId, that.calendarDetailId) && Objects.equals(classRoomId, that.classRoomId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendarDetailId, classRoomId, courseId);
    }
}
