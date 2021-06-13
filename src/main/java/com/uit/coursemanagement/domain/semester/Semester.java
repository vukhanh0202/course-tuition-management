package com.uit.coursemanagement.domain.semester;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.course.OpenCourse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "semester")
public class Semester extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Date fromDate;

    private Date toDate;

    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.OPEN;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "semester")
    private List<OpenCourse> openCourses = new ArrayList<>();

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return name + "(" + dateFormat.format(fromDate) + " - " + dateFormat.format(toDate) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Semester semester = (Semester) o;
        return Objects.equals(id, semester.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
