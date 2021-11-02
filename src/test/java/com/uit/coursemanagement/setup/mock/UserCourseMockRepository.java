package com.uit.coursemanagement.setup.mock;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.setup.wrapper.TuitionFeeWrapper;
import com.uit.coursemanagement.setup.wrapper.UserCourseWrapper;
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
public class UserCourseMockRepository implements BaseMockRepository<UserCourseWrapper>   {

    @Override
    public void setUpMock(UserCourseWrapper userCourseWrapper) {
        StudentCourse input = userCourseWrapper.getInput();
        StudentCourse output = userCourseWrapper.getOutput();
        List<StudentCourse> result = new ArrayList<>();
        if (output != null) result.add(output);
        Mockito.when(userCourseWrapper.getUserCourseRepository().findAllByStudentIdAndOpenCourseSemesterId(input.getStudent().getId(),
                input.getOpenCourse().getSemester().getId()))
                .thenReturn(result);
    }
}
