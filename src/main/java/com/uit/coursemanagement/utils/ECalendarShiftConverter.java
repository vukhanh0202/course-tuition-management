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
        if (stringList == null){
            return "";
        }
        ECalendarShift[] array = new ECalendarShift[stringList.size()];
        stringList.toArray(array);
        return  Stream.of(array)
                .map(Enum::name)
                .collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<ECalendarShift> convertToEntityAttribute(String string) {
        return string != null ? Arrays.stream(string.split(SPLIT_CHAR))
                .map(ECalendarShift::valueOf)
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}
