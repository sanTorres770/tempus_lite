package com.santorres.tempus_lite.employee.domain;

import java.util.List;

public interface EmployeeRepository {
    List<EmployeeData> getAll();

    boolean saveNewEmployee(Employee employee);

    EmployeeData getEmployeeById(String documentId);

    boolean updateEmployee(Employee employee, String documentId);

    List<EmployeeData> getHeadAreaEmployees();

    List<EmployeeData> getOperatorEmployeesByArea(String fkArea);

    boolean assignEmployeeToArea(String employeeId, String fkArea);

    List<EmployeeData> getOperatorEmployeesAvailable();

    List<EmployeeData> getOperatorEmployeesAvailableByArea(String fkArea);

    void updateEmployeeArea(String employeeId, String areaId);

    List<EmployeeData> getOperatorEmployeesByTask(String fkTask);

    boolean assignEmployeeToTask(String employeeId, String fkTask);
}
