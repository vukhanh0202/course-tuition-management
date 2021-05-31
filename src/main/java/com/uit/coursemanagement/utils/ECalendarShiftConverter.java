package com.uit.coursemanagement.utils;

import com.uit.coursemanagement.constant.enums.ECalendarShift;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class ECalendarShiftConverter implements AttributeConverter<List<ECalendarShift>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<ECalendarShift> stringList) {
//        return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
        return stringList != null ? Stream.of(ECalendarShift.values())
                .map(Enum::name)
                .collect(Collectors.joining(SPLIT_CHAR)) : "";
    }

    @Override
    public List<ECalendarShift> convertToEntityAttribute(String string) {
//        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
        return string != null ? Arrays.stream(string.split(SPLIT_CHAR))
                .map(ECalendarShift::valueOf)
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}
