package com.santorres.tempus_lite.employee_role.domain;

import java.util.List;

public interface EmployeeRoleRepository {
    List<EmployeeRole> getAll();

    void saveAssignedRole(String documentId, int roleId);

    List<EmployeeRole> getRolesByUser(String userId);
}
