package com.santorres.tempus_lite.shared.domian;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class ConvertToLocalTime {

    public static LocalTime convert(Time value) {

        return value == null ? null : value.toLocalTime();
    }
}
