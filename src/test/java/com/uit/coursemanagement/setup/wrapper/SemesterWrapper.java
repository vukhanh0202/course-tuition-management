package com.uit.coursemanagement.setup.wrapper;

import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import lombok.Data;

/**
 * TuitionFeeWrapper
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@Data
public class SemesterWrapper {

    private Semester semesterInput;
    private Semester semesterOutput;
    private SemesterRepository semesterRepository;

    public SemesterWrapper(Semester semesterInput, Semester semesterOutput, SemesterRepository semesterRepository) {
        this.semesterInput = semesterInput;
        this.semesterOutput = semesterOutput;
        this.semesterRepository = semesterRepository;
    }
}
