package com.uit.coursemanagement.constant.enums;

import lombok.Getter;

public enum EDayOfWeek {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    @Getter
    private Integer number;

    EDayOfWeek(Integer number) {
        this.number = number;
    }
}
