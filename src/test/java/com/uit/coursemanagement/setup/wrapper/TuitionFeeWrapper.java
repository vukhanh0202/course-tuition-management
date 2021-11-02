package com.uit.coursemanagement.setup.wrapper;

import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import lombok.Data;

/**
 * TuitionFeeWrapper
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@Data
public class TuitionFeeWrapper {

    private TuitionFee tuitionFeeInput;
    private TuitionFee tuitionFeeOutput;
    private TuitionFeeRepository tuitionFeeRepository;

    public TuitionFeeWrapper(TuitionFee tuitionFeeInput, TuitionFee tuitionFeeOutput, TuitionFeeRepository tuitionFeeRepository) {
        this.tuitionFeeInput = tuitionFeeInput;
        this.tuitionFeeOutput = tuitionFeeOutput;
        this.tuitionFeeRepository = tuitionFeeRepository;
    }
}
