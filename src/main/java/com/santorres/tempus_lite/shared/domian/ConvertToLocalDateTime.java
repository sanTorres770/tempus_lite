package com.santorres.tempus_lite.shared.domian;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class ConvertToLocalDateTime {

    public static LocalDateTime convert(Timestamp value) {

        return value == null ? null : value.toLocalDateTime();
    }
}
