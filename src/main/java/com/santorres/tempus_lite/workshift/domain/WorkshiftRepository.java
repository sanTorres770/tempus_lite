package com.santorres.tempus_lite.workshift.domain;

import java.util.List;

public interface WorkshiftRepository {
    Workshift getWorkshiftByEmployee(String employeeId);

    List<Workshift> getAll();

    void assignEmployeeWorkshift(String idDocument, int workshiftId);
}
