package com.santorres.tempus_lite.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeData {

    private String documentId;
    private String name;
    private String lastName;
    private String telephone;
    private String email;
    private int fkRole;
    private String fkArea;
    private String roleName;
    private String areaName;
}
