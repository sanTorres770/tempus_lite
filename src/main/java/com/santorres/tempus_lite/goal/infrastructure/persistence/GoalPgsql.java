package com.santorres.tempus_lite.goal.infrastructure.persistence;

import com.santorres.tempus_lite.goal.domain.Goal;
import com.santorres.tempus_lite.goal.domain.GoalData;
import com.santorres.tempus_lite.goal.domain.GoalRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoalPgsql implements GoalRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public GoalPgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GoalData> getGoalsByArea(String fkArea) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("fkArea",fkArea);

        String sql = " select g.*, upper(concat(e.name, ' ', e.last_name)) as created_by_name, a.name as area_name from bd_1.goals g " +
                " join bd_1.employees e on e.document_id = g.fk_created_by " +
                " join bd_1.areas a on a.id = g.fk_area" +
                " where fk_area = :fkArea";

        return jdbcTemplate.query(sql,map,new GoalDataRowMapper());

    }

    @Override
    public List<GoalData> getAllGoals() {

        String sql = "select * from bd_1.goals";

        return jdbcTemplate.query(sql,new GoalDataRowMapper());
    }

    @Override
    public boolean saveNewGoal(Goal goal) {

        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("id",goal.getId());
            map.addValue("description",goal.getDescription());
            map.addValue("initialDate",goal.getInitialDate());
            map.addValue("finalDate",goal.getFinalDate());
            map.addValue("false",goal.isFinished());
            map.addValue("observation",goal.getObservation());
            map.addValue("fkArea",goal.getFkArea());
            map.addValue("createdBy",goal.getFkCreatedBy());

            String sql = "insert into bd_1.goals values (" +
                    ":id, " +
                    ":description, " +
                    ":initialDate, " +
                    ":finalDate, " +
                    ":false, " +
                    ":observation, " +
                    ":fkArea, " +
                    ":createdBy)";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
