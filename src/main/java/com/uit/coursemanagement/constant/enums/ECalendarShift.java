package com.uit.coursemanagement.constant.enums;

import lombok.Getter;

@Getter
public enum ECalendarShift {
    SHIFT_1("Ca 1", "7:30","8:15", 27000000L, 29700000L),
    SHIFT_2("Ca 2","8:15","9:00", 29700000L, 32400000L),
    SHIFT_3("Ca 3","9:00","9:45", 32400000L, 35100000L),
    SHIFT_4("Ca 4","10:00","10:45",36000000L, 38700000L),
    SHIFT_5("Ca 5","10:45","11:30", 38700000L, 41400000L),
    SHIFT_6("Ca 6","13:00","13:45", 46800000L, 49500000L),
    SHIFT_7("Ca 7","13:45","14:30", 49500000L, 52200000L),
    SHIFT_8("Ca 8","14:30","15:15", 52200000L, 54900000L),
    SHIFT_9("Ca 9","15:30","16:45", 55800000L, 58500000L),
    SHIFT_10("Ca 10","16:45","17:00", 58500000L, 61200000L);

    @Getter
    private final String value;

    @Getter
    private final String fromTime;

    @Getter
    private final String toTime;

    @Getter
    private final Long timeStart;

    @Getter
    private final Long timeEnd;

    ECalendarShift(String value, String fromTime, String toTime, Long timeStart, Long timeEnd) {
        this.value = value;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getValueString(){
        return value + "(" + fromTime + " - " + toTime + ")";
    }
}
