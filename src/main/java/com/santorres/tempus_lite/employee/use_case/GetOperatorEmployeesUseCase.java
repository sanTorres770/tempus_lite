package com.santorres.tempus_lite.employee.use_case;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOperatorEmployeesUseCase {

    private final EmployeeRepository employeeRepository;

    public GetOperatorEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeData> getOperatorEmployeesByArea(String fkArea){
        return employeeRepository.getOperatorEmployeesByArea(fkArea);
    }

    public List<EmployeeData> getOperatorEmployeesAvailable() {
        return employeeRepository.getOperatorEmployeesAvailable();
    }

    public List<EmployeeData> getOperatorEmployeesByTask(String fkTask) {
        return employeeRepository.getOperatorEmployeesByTask(fkTask);

    }

    public List<EmployeeData> getOperatorEmployeesAvailableByArea(String fkArea) {
        return employeeRepository.getOperatorEmployeesAvailableByArea(fkArea);

    }
}
