package com.uit.coursemanagement.repository.semester;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.semester.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

    List<Semester> findAllByStatus(EStatus status);

    Optional<Semester> findByName(String name);

    Optional<Semester> findByFromDateBetweenOrToDateBetween(Date fromDate1, Date toDate1, Date fromDate2, Date toDate2);
}


