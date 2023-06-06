package com.santorres.tempus_lite.login.infrastructure.persistence;

import com.santorres.tempus_lite.login.domain.LoginRegistry;
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
    public List<LoginRegistry> getLoginRegistryByEmployee(String employeeId, LocalDate today) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("employeeId",employeeId);
        map.addValue("today",today);

        String sql = "select * from bd_1.login_register where login_date=:today and fk_employee=:employeeId ";

        return jdbcTemplate.query(sql, map, new LoginRegistryRowMapper());
    }

    @Override
    public boolean saveFinalHourRegistry(String loginRegistryId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id",loginRegistryId);
        map.addValue("finalHour", LocalTime.now());

        String sql = " update bd_1.login_register set final_hour = :finalHour where id=:id";

        return jdbcTemplate.update(sql,map) > 0;
    }
}
