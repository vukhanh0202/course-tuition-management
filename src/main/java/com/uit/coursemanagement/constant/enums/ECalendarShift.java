package com.uit.coursemanagement.constant.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ECalendarShift {
    SHIFT_1("Ca 1", "7:30", "8:15", 7, 30, 8, 15,1),
    SHIFT_2("Ca 2", "8:15", "9:00", 8, 15, 9, 0,2),
    SHIFT_3("Ca 3", "9:00", "9:45", 9, 0, 9, 45,3),
    SHIFT_4("Ca 4", "10:00", "10:45", 10, 0, 10, 45,4),
    SHIFT_5("Ca 5", "10:45", "11:30", 10, 45, 11, 30,5),
    SHIFT_6("Ca 6", "13:00", "13:45", 13, 0, 13, 45,6),
    SHIFT_7("Ca 7", "13:45", "14:30", 13, 45, 14, 30,7),
    SHIFT_8("Ca 8", "14:30", "15:15", 14, 30, 15, 15,8),
    SHIFT_9("Ca 9", "15:30", "16:45", 15, 30, 16, 45,9),
    SHIFT_10("Ca 10", "16:45", "17:00", 16, 45, 17, 0,10);

    @Getter
    private final String value;

    @Getter
    private final String fromTime;

    @Getter
    private final String toTime;

    @Getter
    private final Integer hourFrom;

    @Getter
    private final Integer minuteFrom;

    @Getter
    private final Integer hourTo;

    @Getter
    private final Integer minuteTo;

    @Getter
    private final Integer lesson;

    ECalendarShift(String value, String fromTime, String toTime, Integer hourFrom, Integer minuteFrom,
                   Integer hourTo, Integer minuteTo, Integer lesson) {
        this.value = value;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.hourFrom = hourFrom;
        this.minuteFrom = minuteFrom;
        this.hourTo = hourTo;
        this.minuteTo = minuteTo;
        this.lesson = lesson;
    }

    public String getValueString() {
        return value + "(" + fromTime + " - " + toTime + ")";
    }

    public static Map<String, String> getMapEnum() {
        Map<String, String> rs = new HashMap<>();
        Stream.of(ECalendarShift.values()).collect(Collectors.toList()).forEach(eCalendarShift -> {
            rs.put(eCalendarShift.name(), eCalendarShift.getValueString());
        });
        return rs;
    }
}
