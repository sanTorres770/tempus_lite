package com.santorres.tempus_lite.employee.use_case;

import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class AssignEmployeeToAreaUseCase {
    private final EmployeeRepository employeeRepository;

    public AssignEmployeeToAreaUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean assignEmployeeToArea(String employeeId, String fkArea){
        return employeeRepository.assignEmployeeToArea(employeeId,fkArea);
    }
}
