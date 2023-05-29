package com.santorres.tempus_lite.working_area.infrastructure.persistence;

import com.santorres.tempus_lite.employee.infrastructure.persistence.EmployeeDataRowMapper;
import com.santorres.tempus_lite.working_area.domain.WorkingArea;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaData;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkingAreaPgsql implements WorkingAreaRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WorkingAreaPgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean saveNewWorkingArea(WorkingArea workingArea) {
        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("id",workingArea.getId());
            map.addValue("name",workingArea.getName());
            map.addValue("fkAreaBoss",workingArea.getFkAreaBoss());

            String sql = "insert into bd_1.areas values (" +
                    ":id, " +
                    ":name, " +
                    ":fkAreaBoss)";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<WorkingAreaData> getAll() {

        String sql = " select areas.*, upper(concat(e.name , ' ' , e.last_name)) as boss_name " +
                " from bd_1.areas " +
                " join bd_1.employees e on e.document_id = areas.fk_area_boss";

        return jdbcTemplate.query(sql, new WorkingAreaDataRowMapper());
    }

}
