package com.uit.coursemanagement.constant;

public interface MessageCode {
    interface User {
        String WRONG = "message.user.Wrong";
        String WRONG_PASS = "message.user.WrongPass";
        String INVALID = "message.user.Invalid";
        String NOT_FOUND = "message.user.NotFound";
        String EXIST = "message.user.Exist";
    }
    interface Student {
        String FULL_COURSE_IN_SEMESTER = "message.student.fullCourse";
        String EXIST_COURSE_REGISTERED = "message.student.Registered";
        String PAYMENT_RESOLVED = "message.student.PaymentResolved";
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
        String DATE_INVALID = "message.semester.DateInvalid";
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
        String WAS_REGISTERED = "message.openCourse.WasRegistered";
        String WAS_REGISTERED_IN_ANOTHER_IN_CLASS = "message.openCourse.WasRegisteredInAnotherClass";
    }

    interface Tuition {
        String NOT_FOUND = "message.tuition.NotFound";
        String IS_COMPLETED = "message.tuition.IsCompleted";
    }
}
