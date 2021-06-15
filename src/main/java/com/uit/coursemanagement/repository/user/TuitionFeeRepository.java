package com.uit.coursemanagement.repository.user;

import com.uit.coursemanagement.domain.tuition.TuitionFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TuitionFeeRepository extends JpaRepository<TuitionFee, Long> {

    List<TuitionFee> findAllByStudentIdAndSemesterId(Long studentId, Long semesterId);

}