package com.santorres.tempus_lite.employee.use_case;

import com.santorres.tempus_lite.employee.domain.Employee;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean updateEmployee(Map<String,String> data, String documentId){

        Employee employee = createEmployee(data);

        return employeeRepository.updateEmployee(employee,documentId);
    }

    private Employee createEmployee(Map<String, String> data) {

        return new Employee(
                data.get("idDocument"),
                data.get("name"),
                data.get("lastName"),
                data.get("telephone"),
                data.get("email"),
                Integer.parseInt(data.get("fkRole"))
        );
    }
}
