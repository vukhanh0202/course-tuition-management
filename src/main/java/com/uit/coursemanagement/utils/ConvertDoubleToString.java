package com.uit.coursemanagement.utils;

import java.text.DecimalFormat;

public class ConvertDoubleToString {

    public static String convert(Double number){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(number) + " VNĐ";
    }
}
