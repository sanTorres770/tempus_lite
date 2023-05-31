package com.santorres.tempus_lite.workshift.domain;

public interface WorkshiftRepository {
    Workshift getWorkshiftByEmployee(String employeeId);
}
