package com.santorres.tempus_lite.employee.use_case;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetHeadAreaEmployeesUseCase {
    private final EmployeeRepository employeeRepository;

    public GetHeadAreaEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeData> getHeadAreaEmployees(){
        return employeeRepository.getHeadAreaEmployees();
    }
}
