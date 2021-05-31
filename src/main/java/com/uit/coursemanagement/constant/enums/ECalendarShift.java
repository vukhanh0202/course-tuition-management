package com.uit.coursemanagement.constant.enums;

import lombok.Getter;

public enum ECalendarShift {
    SHIFT_1("7:30","8:15"),
    SHIFT_2("8:15","9:00"),
    SHIFT_3("9:00","9:45"),
    SHIFT_4("10:00","10:45"),
    SHIFT_5("10:45","11:30"),
    SHIFT_6("13:00","13:45"),
    SHIFT_7("13:45","14:30"),
    SHIFT_8("14:30","15:15"),
    SHIFT_9("15:30","16:45"),
    SHIFT_10("16:45","17:00");

    @Getter
    private final String fromTime;

    @Getter
    private final String toTime;

    ECalendarShift(String fromTime, String toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
}
