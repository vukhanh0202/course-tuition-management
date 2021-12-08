package com.uit.coursemanagement.setup.mock;

import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.setup.wrapper.UserCourseWrapper;
import com.uit.coursemanagement.setup.wrapper.UserWrapper;
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
public class UserMockRepository implements BaseMockRepository<UserWrapper>   {

    @Override
    public void setUpMock(UserWrapper userWrapper) {
        User input = userWrapper.getInput();
        User output = userWrapper.getOutput();
        Mockito.when(userWrapper.getUserRepository().findById(input.getId()))
                .thenReturn(Optional.ofNullable(output));
    }
}
