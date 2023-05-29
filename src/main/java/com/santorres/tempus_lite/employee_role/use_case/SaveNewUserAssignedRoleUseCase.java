package com.santorres.tempus_lite.employee_role.use_case;

import com.santorres.tempus_lite.employee_role.domain.EmployeeRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveNewUserAssignedRoleUseCase {

    private final EmployeeRoleRepository employeeRoleRepository;

    public SaveNewUserAssignedRoleUseCase(EmployeeRoleRepository employeeRoleRepository) {
        this.employeeRoleRepository = employeeRoleRepository;
    }

    public void saveAssignedRole(String documentId, int roleId){
        employeeRoleRepository.saveAssignedRole(documentId,roleId);
    }
}
