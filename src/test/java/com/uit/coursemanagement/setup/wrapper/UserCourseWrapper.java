package com.uit.coursemanagement.setup.wrapper;

import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import lombok.Data;

/**
 * TuitionFeeWrapper
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@Data
public class UserCourseWrapper {

    private StudentCourse input;
    private StudentCourse output;
    private UserCourseRepository userCourseRepository;

    public UserCourseWrapper(StudentCourse input, StudentCourse output, UserCourseRepository userCourseRepository) {
        this.input = input;
        this.output = output;
        this.userCourseRepository = userCourseRepository;
    }
}
