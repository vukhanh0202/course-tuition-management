package com.uit.coursemanagement.domain.tuition;

import com.uit.coursemanagement.constant.enums.EFeeStatus;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.SqlEntity;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.user.Role;
import com.uit.coursemanagement.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tuition_fee")
public class TuitionFee extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    private Date timeCompleted;

    private Double totalFee;

    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.PENDING;
}
