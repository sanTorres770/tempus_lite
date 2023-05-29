package com.santorres.tempus_lite.employee_role.use_case;

import com.santorres.tempus_lite.employee_role.domain.EmployeeRole;
import com.santorres.tempus_lite.employee_role.domain.EmployeeRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllEmployeeRolesUseCase {

    private final EmployeeRoleRepository employeeRoleRepository;

    public GetAllEmployeeRolesUseCase(EmployeeRoleRepository employeeRoleRepository) {
        this.employeeRoleRepository = employeeRoleRepository;
    }

    public List<EmployeeRole> getAll(){
        return employeeRoleRepository.getAll();
    }
}
