package com.uit.coursemanagement.domain.tuition;

import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.calendar.join.CourseCalendarCreation;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tuition_fee")
public class TuitionFee extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "calendar_detail_id", insertable = false, updatable = false),
            @JoinColumn(name = "class_room_id", insertable = false, updatable = false),
            @JoinColumn(name = "course_id", insertable = false, updatable = false),
            @JoinColumn(name = "student_id", insertable = false, updatable = false)
    })
    private CourseCalendarCreation courseCalendarCreation;

    private Double priceTuitionFee;

    private Boolean isCompleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TuitionFee that = (TuitionFee) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
