package com.uit.coursemanagement.repository.semester;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.semester.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

    List<Semester> findAllByStatus(EStatus status);

    Optional<Semester> findByName(String name);

    @Query(value = "select * from semester " +
            " where from_date >= :fromDate AND from_date <= :toDate" +
            " OR from_date >= :fromDate AND to_date <= :toDate" +
            " OR from_date <= :fromDate AND to_date >= :toDate",
            nativeQuery = true)
    List<Semester> findAllByFromDateAndToDate(Date fromDate, Date toDate);


}


