package com.santorres.tempus_lite.login.infrastructure.persistence;

import com.santorres.tempus_lite.login.domain.LoginRegistryData;
import com.santorres.tempus_lite.shared.domian.ConvertToDate;
import com.santorres.tempus_lite.shared.domian.ConvertToLocalTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoginRegistryDataRowMapper implements RowMapper<LoginRegistryData> {
    @Override
    public LoginRegistryData mapRow(ResultSet rs, int rowNum) throws SQLException {

        String id = rs.getString("id");
        LocalDate loginDate = ConvertToDate.convert(rs.getDate("login_date"));
        LocalTime loginHour = ConvertToLocalTime.convert(rs.getTime("login_hour"));
        LocalTime finalHour = ConvertToLocalTime.convert(rs.getTime("final_hour"));
        String fkEmployee = rs.getString("fk_employee");
        String employeeName = rs.getString("employee_name");
        String areaName = rs.getString("area_name");

        return new LoginRegistryData(
                id,
                loginDate,
                loginHour,
                finalHour,
                fkEmployee,
                employeeName,
                areaName,
                null,
                null
        );
    }
}
