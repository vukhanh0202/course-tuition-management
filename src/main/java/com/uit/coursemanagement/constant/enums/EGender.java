package com.uit.coursemanagement.constant.enums;

import lombok.Getter;

public enum EGender {
    MALE("Nam"),
    FEMALE("Nữ");

    @Getter
    private String value;

    EGender(String value){
        this.value = value;
    }
}
