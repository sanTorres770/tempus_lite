package com.santorres.tempus_lite.employee.use_case;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeByIdUseCase {
    private final EmployeeRepository employeeRepository;

    public GetEmployeeByIdUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeData getEmployeeById(String documentId){
        return employeeRepository.getEmployeeById(documentId);
    }
}
