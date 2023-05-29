package com.santorres.tempus_lite.shared.domian;

public final class NullToBlank {

    public static String modify (String value){

        return value == null ? "" : value;
    }
}
