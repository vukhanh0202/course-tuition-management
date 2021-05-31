package com.uit.coursemanagement.constant;

public interface MessageCode {
    interface User {
        String WRONG = "message.user.Wrong";
        String INVALID = "message.user.Invalid";
        String NOT_FOUND = "message.user.NotFound";
    }
    interface Token {
        String INVALID_TOKEN = "message.token.InvalidToken";
    }
    interface Course {
        String EXIST = "message.course.exists";
    }
}
