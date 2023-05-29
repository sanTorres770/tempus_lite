package com.santorres.tempus_lite.employee.use_case;

import com.santorres.tempus_lite.employee.domain.Employee;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveNewEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public SaveNewEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean saveNewEmployee(Employee employee){
        return employeeRepository.saveNewEmployee(employee);
    }
}
