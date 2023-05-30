package com.santorres.tempus_lite.employee.infrastructure.persistence;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDataRowMapper implements RowMapper<EmployeeData> {
    @Override
    public EmployeeData mapRow(ResultSet rs, int rowNum) throws SQLException {

        String documentId = rs.getString("document_id");
        String name = rs.getString("name");
        String lastName = rs.getString("last_name");
        String telephone = rs.getString("telephone");
        String email = rs.getString("email");
        int fkRole = rs.getInt("fk_role");
        String fkArea = rs.getString("fk_area");
        String roleName = rs.getString("role_name");
        String areaName = rs.getString("area_name");

        return new EmployeeData(
                documentId,
                name,
                lastName,
                telephone,
                email,
                fkRole,
                fkArea,
                roleName,
                areaName
        );
    }
}
