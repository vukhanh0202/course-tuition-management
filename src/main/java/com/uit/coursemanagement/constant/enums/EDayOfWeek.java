package com.uit.coursemanagement.constant.enums;

import lombok.Getter;

public enum EDayOfWeek {
    MONDAY(1, "Thứ 2"),
    TUESDAY(2,"Thứ 3"),
    WEDNESDAY(3,"Thứ 4"),
    THURSDAY(4,"Thứ 5"),
    FRIDAY(5,"Thứ 6"),
    SATURDAY(6,"Thứ 7"),
    SUNDAY(7,"Chủ Nhật");

    @Getter
    private Integer number;

    @Getter
    private String value;

    EDayOfWeek(Integer number, String value) {
        this.number = number;
        this.value = value;
    }
}
