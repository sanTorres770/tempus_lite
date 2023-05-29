package com.santorres.tempus_lite.shared.domian;

public final class StringToDoubleOrNull {

    public static Double convert(String value){
        return value == null || value.isEmpty() ? null : Double.parseDouble(value);
    }

}
