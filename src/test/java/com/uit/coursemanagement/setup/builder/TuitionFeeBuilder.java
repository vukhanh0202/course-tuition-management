package com.uit.coursemanagement.setup.builder;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.tuition.TuitionFee;

/**
 * TuitionFeeBuilder
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
public class TuitionFeeBuilder {
    private Long id;
    private EStatus status;
    private Long studentId;
    private Long semesterId;
    private Double totalFee;

    public TuitionFeeBuilder id(Long id){
        this.id = id;
        return this;
    }

    public TuitionFeeBuilder status(EStatus status){
        this.status = status;
        return this;
    }

    public TuitionFeeBuilder studentId(Long studentId){
        this.studentId = studentId;
        return this;
    }

    public TuitionFeeBuilder semesterId(Long semesterId){
        this.semesterId = semesterId;
        return this;
    }

    public TuitionFeeBuilder totalFee(Double totalFee){
        this.totalFee = totalFee;
        return this;
    }

    public TuitionFee build() {
        TuitionFee tuitionFee = new TuitionFee();
        tuitionFee.setId(id);
        tuitionFee.setStatus(status);
        Student student = new Student();
        student.setId(studentId);
        tuitionFee.setStudent(student);
        Semester semester = new Semester();
        semester.setId(semesterId);
        tuitionFee.setSemester(semester);
        tuitionFee.setTotalFee(totalFee);
        return tuitionFee;
    }
}
