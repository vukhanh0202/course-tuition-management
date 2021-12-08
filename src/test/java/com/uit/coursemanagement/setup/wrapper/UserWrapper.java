package com.uit.coursemanagement.setup.wrapper;

import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import lombok.Data;

/**
 * TuitionFeeWrapper
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@Data
public class UserWrapper {

    private User input;
    private User output;
    private UserRepository userRepository;

    public UserWrapper(User input, User output, UserRepository userRepository) {
        this.input = input;
        this.output = output;
        this.userRepository = userRepository;
    }
}
