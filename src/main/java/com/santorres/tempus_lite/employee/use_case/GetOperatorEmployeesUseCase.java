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
}
