package com.santorres.tempus_lite.employee.use_case;

import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class AssignEmployeeToTaskUseCase {

    private final EmployeeRepository employeeRepository;

    public AssignEmployeeToTaskUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean assignEmployeeToTask(String employeeId, String fkTask){
        return employeeRepository.assignEmployeeToTask(employeeId,fkTask);
    }
}
