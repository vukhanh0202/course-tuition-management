package com.uit.coursemanagement.service.tuition;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.setup.builder.MockBuilder;
import com.uit.coursemanagement.setup.builder.SemesterBuilder;
import com.uit.coursemanagement.setup.builder.TuitionFeeBuilder;
import com.uit.coursemanagement.setup.builder.UserCourseBuilder;
import com.uit.coursemanagement.setup.wrapper.SemesterWrapper;
import com.uit.coursemanagement.setup.wrapper.TuitionFeeWrapper;
import com.uit.coursemanagement.setup.wrapper.UserCourseWrapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TuitionControllerTest
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfirmTuitionServiceTest {

    @Autowired
    private IConfirmTuitionService confirmTuitionService;

    @MockBean
    private TuitionFeeRepository tuitionFeeRepository;

    @MockBean
    private UserCourseRepository userCourseRepository;

    @Test
    public void execute_UTCID01_TuitionFeeNotFound_ExceptionFail() {
        Long tuitionId = null;
        var tuitionFee = new TuitionFeeBuilder().id(tuitionId).build();
        new MockBuilder().tuitionFee(new TuitionFeeWrapper(tuitionFee, null, tuitionFeeRepository)).build();
        var input = new IConfirmTuitionService.Input(tuitionId, true);
        Assertions.assertThrows(NotFoundException.class, () -> confirmTuitionService.execute(input));
    }

    @Test
    public void execute_UTCID02_TuitionFeeCompleted_ExceptionFail() {
        Long tuitionId = -1L;
        var tuitionFee = new TuitionFeeBuilder()
                .id(tuitionId)
                .status(EStatus.COMPLETED)
                .studentId(1L)
                .build();
        new MockBuilder().tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository)).build();
        var input = new IConfirmTuitionService.Input(tuitionId, true);
        Assertions.assertThrows(InvalidException.class, () -> confirmTuitionService.execute(input));
    }

    @Test
    public void execute_UTCID03_ExceptionFail() {
        Long tuitionId = 1L;
        var tuitionFee = new TuitionFeeBuilder()
                .id(tuitionId)
                .status(EStatus.PENDING)
                .studentId(1L)
                .totalFee(1D)
                .build();
        var userCourse = new UserCourseBuilder()
                .semesterId(1L)
                .studentId(1L)
                .build();
        new MockBuilder()
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .build();
        var input = new IConfirmTuitionService.Input(tuitionId, true);
        Assertions.assertTrue(confirmTuitionService.execute(input));
    }

}
