package com.santorres.tempus_lite.login.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class LoginRegistryData {
    private String id;
    private LocalDate loginDate;
    private LocalTime loginHour;
    private LocalTime finalHour;
    private String fkEmployee;
    private String employeeName;
    private String areaName;
    private String workedHours;
    private Double workedMinutes;
}
