package com.uit.coursemanagement.constant.enums;

import lombok.Getter;

@Getter
public enum ECalendarShift {
    SHIFT_1("Ca 1", "7:30","8:15"),
    SHIFT_2("Ca 2","8:15","9:00"),
    SHIFT_3("Ca 3","9:00","9:45"),
    SHIFT_4("Ca 4","10:00","10:45"),
    SHIFT_5("Ca 5","10:45","11:30"),
    SHIFT_6("Ca 6","13:00","13:45"),
    SHIFT_7("Ca 7","13:45","14:30"),
    SHIFT_8("Ca 8","14:30","15:15"),
    SHIFT_9("Ca 9","15:30","16:45"),
    SHIFT_10("Ca 10","16:45","17:00");

    @Getter
    private final String value;

    @Getter
    private final String fromTime;

    @Getter
    private final String toTime;

    ECalendarShift(String value, String fromTime, String toTime) {
        this.value = value;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getValueString(){
        return value + "(" + fromTime + " - " + toTime + ")";
    }
}
