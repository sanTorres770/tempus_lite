package com.santorres.tempus_lite.login.infrastructure.persistence;

import com.santorres.tempus_lite.login.domain.LoginRegistry;
import com.santorres.tempus_lite.login.domain.LoginRegistryData;
import com.santorres.tempus_lite.login.domain.LoginRegistryRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class LoginRegistryPgsql implements LoginRegistryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LoginRegistryPgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveInitialLoginRegistry(LoginRegistry loginRegistry) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id",loginRegistry.getId());
        map.addValue("loginDate",loginRegistry.getLoginDate());
        map.addValue("loginHour",loginRegistry.getLoginHour());
        map.addValue("fkEmployee",loginRegistry.getFkEmployee());

        String sql = "insert into bd_1.login_register values (" +
                " :id, " +
                " :loginDate, " +
                " :loginHour, " +
                " :fkEmployee)";

        jdbcTemplate.update(sql,map);

    }

    @Override
    public List<LoginRegistryData> getLoginRegistryByEmployee(String employeeId, LocalDate today) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("employeeId",employeeId);
        map.addValue("today",today);

        String sql = "select lr.*, upper(concat(en.name,' ',en.last_name)) as employee_name, a.name as area_name " +
                " from bd_1.login_register lr " +
                " join bd_1.employees en on en.document_id = lr.fk_employee" +
                " left outer join bd_1.areas a on en.fk_area = a.id" +
                " where lr.login_date=:today and lr.fk_employee=:employeeId ";

        return jdbcTemplate.query(sql, map, new LoginRegistryDataRowMapper());
    }

    @Override
    public boolean saveFinalHourRegistry(String loginRegistryId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id",loginRegistryId);
        map.addValue("finalHour", LocalTime.now());

        String sql = " update bd_1.login_register set final_hour = :finalHour where id=:id";

        return jdbcTemplate.update(sql,map) > 0;
    }

    @Override
    public List<LoginRegistryData> getLoginRegistryList(LocalDate date1, LocalDate date2) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("date1",date1);
        map.addValue("date2",date2);

        String sql = "select lr.*, upper(concat(en.name,' ',en.last_name)) as employee_name, a.name as area_name " +
                " from bd_1.login_register lr " +
                " join bd_1.employees en on en.document_id = lr.fk_employee" +
                " left outer join bd_1.areas a on en.fk_area = a.id" +
                " where lr.login_date between :date1 and :date2 order by login_date";

        return jdbcTemplate.query(sql, map, new LoginRegistryDataRowMapper());
    }
}
