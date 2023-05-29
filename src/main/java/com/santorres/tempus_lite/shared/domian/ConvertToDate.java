package com.santorres.tempus_lite.shared.domian;

import java.sql.Date;
import java.time.LocalDate;

public final class ConvertToDate {

    public static LocalDate convert(Date value) {

        return value == null ? null : value.toLocalDate();
    }
}
