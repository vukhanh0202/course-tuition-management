package com.uit.coursemanagement.constant;

public interface MessageCode {
    interface User {
        String WRONG = "message.user.Wrong";
        String INVALID = "message.user.Invalid";
        String NOT_FOUND = "message.user.NotFound";
    }
    interface Student {
        String FULL_COURSE_IN_SEMESTER = "message.student.fullCourse";
        String EXIST_COURSE_REGISTERED = "message.student.Registered";
    }
    interface Token {
        String INVALID_TOKEN = "message.token.InvalidToken";
    }
    interface Course {
        String EXIST = "message.course.exists";
        String NOT_FOUND = "message.course.NotFound";
    }
    interface Semester {
        String EXIST = "message.semester.exists";
        String EXIST_TIME = "message.semester.ExistTime";
        String NOT_FOUND = "message.semester.NotFound";
        String INVALID = "message.semester.Invalid";
        String NOT_DELETED = "message.semester.NotDeleted";
    }
    interface ClassRoom {
        String EXIST = "message.class.exists";
        String NOT_FOUND = "message.class.NotFound";
        String INVALID = "message.class.Invalid";
        String NOT_DELETED = "message.class.notDeleted";
    }
    interface OpenCourse {
        String NOT_FOUND = "message.openCourse.NotFound";
        String FULL_SIZE = "message.openCourse.FullSize";
    }
}
