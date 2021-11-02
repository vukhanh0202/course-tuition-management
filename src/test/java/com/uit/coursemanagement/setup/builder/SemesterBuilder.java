package com.uit.coursemanagement.setup.builder;

import com.uit.coursemanagement.domain.semester.Semester;
import lombok.Data;

/**
 * TuitionFeeBuilder
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@Data
public class SemesterBuilder {
    private Long id;

    public SemesterBuilder id(Long semesterId){
        this.id = semesterId;
        return this;
    }

    public Semester build() {
        Semester semester = new Semester();
        semester.setId(id);
        return semester;
    }

}
