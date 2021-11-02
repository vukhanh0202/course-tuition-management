package com.uit.coursemanagement.setup.mock;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.setup.wrapper.TuitionFeeWrapper;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TuitionFeeMockRepository
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
public class TuitionFeeMockRepository implements BaseMockRepository<TuitionFeeWrapper>   {

    @Override
    public void setUpMock(TuitionFeeWrapper tuitionFeeWrapper) {
        TuitionFee input = tuitionFeeWrapper.getTuitionFeeInput();
        TuitionFee output = tuitionFeeWrapper.getTuitionFeeOutput();
        List<TuitionFee> result = new ArrayList<>();
        if (output != null) result.add(output);
        Mockito.when(tuitionFeeWrapper.getTuitionFeeRepository().findById(input.getId()))
                .thenReturn(Optional.ofNullable(output));
        Mockito.when(tuitionFeeWrapper.getTuitionFeeRepository()
                .findAllByStudentIdAndSemesterIdAndStatus(input.getStudent().getId(), input.getSemester().getId(), EStatus.COMPLETED))
                .thenReturn(result);
        Mockito.when(tuitionFeeWrapper.getTuitionFeeRepository()
                .save(input))
                .thenReturn(output);
    }
}
