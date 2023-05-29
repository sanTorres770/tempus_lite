package com.santorres.tempus_lite.employee_role.infrastructure.persistence;

import com.santorres.tempus_lite.employee_role.domain.EmployeeRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRoleRowMapper implements RowMapper<EmployeeRole> {
    @Override
    public EmployeeRole mapRow(ResultSet rs, int rowNum) throws SQLException {

        int id = rs.getInt("id");
        String name = rs.getString("name");
        String roleName = rs.getString("role_name");

        return new EmployeeRole(
                id,
                name,
                roleName
        );
    }
}
