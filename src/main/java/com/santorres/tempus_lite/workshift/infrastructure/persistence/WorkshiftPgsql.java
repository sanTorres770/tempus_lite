package com.santorres.tempus_lite.workshift.infrastructure.persistence;

import com.santorres.tempus_lite.workshift.domain.Workshift;
import com.santorres.tempus_lite.workshift.domain.WorkshiftRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkshiftPgsql implements WorkshiftRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WorkshiftPgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Workshift getWorkshiftByEmployee(String employeeId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("employeeId",employeeId);

        String sql = "select w.* from bd_1.workshifts w" +
                " join bd_1.assigned_workshift a on a.fk_workshift = w.id " +
                " join bd_1.employees e on e.document_id = a.fk_employee " +
                " where a.fk_employee = :employeeId";

        return jdbcTemplate.queryForObject(sql,map,new WorkshiftRowMapper());
    }
}
