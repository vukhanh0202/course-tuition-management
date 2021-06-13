package com.uit.coursemanagement.domain.student.join;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class StudentCourseId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long studentId;
    private Long openCourseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseId that = (StudentCourseId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(openCourseId, that.openCourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, openCourseId);
    }
}
