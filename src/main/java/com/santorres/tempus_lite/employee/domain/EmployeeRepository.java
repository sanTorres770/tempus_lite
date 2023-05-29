package com.santorres.tempus_lite.employee.domain;

import java.util.List;

public interface EmployeeRepository {
    List<EmployeeData> getAll();

    boolean saveNewEmployee(Employee employee);

    EmployeeData getEmployeeById(String documentId);

    boolean updateEmployee(Employee employee, String documentId);

    List<EmployeeData> getHeadAreaEmployees();
}
