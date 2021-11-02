package com.uit.coursemanagement.setup.mock;

import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.setup.wrapper.SemesterWrapper;
import org.mockito.Mockito;

import java.util.Optional;

/**
 * TuitionFeeMockRepository
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
public class SemesterMockRepository implements BaseMockRepository<SemesterWrapper>  {

    @Override
    public void setUpMock(SemesterWrapper semesterWrapper) {
        Semester input = semesterWrapper.getSemesterInput();
        Semester output = semesterWrapper.getSemesterOutput();
        Mockito.when(semesterWrapper.getSemesterRepository().findById(input.getId()))
                .thenReturn(Optional.ofNullable(output));
    }
}
