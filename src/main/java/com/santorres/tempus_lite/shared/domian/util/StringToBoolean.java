package com.santorres.tempus_lite.shared.domian.util;

public final class StringToBoolean {

    public static Boolean convert(String value){
        return value != null && !value.equals("N");
    }
}
