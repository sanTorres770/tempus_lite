package com.santorres.tempus_lite.workshift.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Workshift {
    private String id;
    private LocalTime initialHour;
    private LocalTime finalHour;
}
