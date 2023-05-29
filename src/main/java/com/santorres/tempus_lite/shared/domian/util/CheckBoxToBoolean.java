package com.santorres.tempus_lite.shared.domian.util;

public final class CheckBoxToBoolean {

    public static Boolean convert(String value){
        return value == null || value.isEmpty() ? false : true;
    }
}
