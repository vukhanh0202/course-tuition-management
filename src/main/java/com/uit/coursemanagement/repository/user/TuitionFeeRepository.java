package com.uit.coursemanagement.repository.user;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Long> {

    List<TuitionFee> findAllByStudentIdAndSemesterIdAndStatus(Long studentId, Long semesterId, EStatus status);

    List<TuitionFee> findAllByStudentUserFullNameContainingAndSemesterIdAndStatus(String fullName, Long semesterId, EStatus status);

    List<TuitionFee> findAllByStudentIdAndStatus(Long studentId, EStatus status);

}