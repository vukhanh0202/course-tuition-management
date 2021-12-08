package com.uit.coursemanagement.setup.builder;

import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.setup.mock.SemesterMockRepository;
import com.uit.coursemanagement.setup.mock.TuitionFeeMockRepository;
import com.uit.coursemanagement.setup.mock.UserCourseMockRepository;
import com.uit.coursemanagement.setup.mock.UserMockRepository;
import com.uit.coursemanagement.setup.wrapper.SemesterWrapper;
import com.uit.coursemanagement.setup.wrapper.TuitionFeeWrapper;
import com.uit.coursemanagement.setup.wrapper.UserCourseWrapper;
import com.uit.coursemanagement.setup.wrapper.UserWrapper;
import lombok.Data;

/**
 * MockBuilder
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@Data
public class MockBuilder {
    private SemesterWrapper semesterWrapper;
    private TuitionFeeWrapper tuitionFeeWrapper;
    private UserCourseWrapper userCourseWrapper;
    private UserWrapper userWrapper;

    public MockBuilder semester(SemesterWrapper semesterWrapper) {
        this.semesterWrapper = semesterWrapper;
        return this;
    }

    public MockBuilder tuitionFee(TuitionFeeWrapper tuitionFeeWrapper) {
        this.tuitionFeeWrapper = tuitionFeeWrapper;
        return this;
    }

    public MockBuilder userCourse(UserCourseWrapper userCourseWrapper) {
        this.userCourseWrapper = userCourseWrapper;
        return this;
    }

    public MockBuilder user(UserWrapper userWrapper) {
        this.userWrapper = userWrapper;
        return this;
    }

    public void build() {
        if (semesterWrapper != null) {
            var semesterMockRepository = new SemesterMockRepository();
            semesterMockRepository.setUpMock(semesterWrapper);
        }
        if (tuitionFeeWrapper != null) {
            var tuitionFeeMockRepository = new TuitionFeeMockRepository();
            tuitionFeeMockRepository.setUpMock(tuitionFeeWrapper);
        }
        if (userCourseWrapper != null) {
            var userCourseMockRepository = new UserCourseMockRepository();
            userCourseMockRepository.setUpMock(userCourseWrapper);
        }
        if (userWrapper != null) {
            var userMockRepository = new UserMockRepository();
            userMockRepository.setUpMock(userWrapper);
        }
    }
}
