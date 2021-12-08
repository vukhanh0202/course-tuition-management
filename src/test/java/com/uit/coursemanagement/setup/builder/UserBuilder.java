package com.uit.coursemanagement.setup.builder;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.user.User;

/**
 * TuitionFeeBuilder
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
public class UserBuilder {
    private Long id;
    private EUserType userType;

    public UserBuilder id(Long id){
        this.id = id;
        return this;
    }

    public UserBuilder userType(EUserType userType){
        this.userType = userType;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setUserType(userType);
        return user;
    }
}
