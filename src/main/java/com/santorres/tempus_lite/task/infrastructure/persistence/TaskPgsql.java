package com.santorres.tempus_lite.task.infrastructure.persistence;

import com.santorres.tempus_lite.task.domain.Task;
import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskPgsql implements TaskRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TaskPgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TaskData> getTasksByGoal(String goalId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("goalId",goalId);

        String sql = " select t.*, upper(concat(afn.name, ' ', afn.last_name)) as assigned_for_name, " +
                " upper(concat(atn.name, ' ', atn.last_name)) as assigned_to_name, g.description as goal_name" +
                " from bd_1.tasks t " +
                " join bd_1.employees afn on t.fk_assigned_for = afn.document_id " +
                " join bd_1.employees atn on atn.document_id = t.fk_assigned_to " +
                " join bd_1.goals g on g.id = t.fk_goal " +
                " where t.fk_goal = :goalId";

        return jdbcTemplate.query(sql,map,new TaskDataRowMapper());
    }

    @Override
    public boolean saveNewTask(Task task) {
        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("id",task.getId());
            map.addValue("description",task.getDescription());
            map.addValue("initialDate",task.getInitialDate());
            map.addValue("finalDate",task.getFinalDate());
            map.addValue("progress",task.getProgress());
            map.addValue("false",task.isFinished());
            map.addValue("observation",task.getObservation());
            map.addValue("fkAssignedFor",task.getFkAssignedFor());
            map.addValue("fkAssignedTo",task.getFkAssignedTo());
            map.addValue("fkGoal",task.getFkGoal());

            String sql = "insert into bd_1.tasks values (" +
                    ":id, " +
                    ":description, " +
                    ":initialDate, " +
                    ":finalDate, " +
                    ":progress, " +
                    ":false, " +
                    ":observation, " +
                    ":fkAssignedFor, " +
                    ":fkAssignedTo, " +
                    ":fkGoal)";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public TaskData getTaskById(String id) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("id",id);

        String sql = " select t.*, upper(concat(afn.name, ' ', afn.last_name)) as assigned_for_name, " +
                " upper(concat(atn.name, ' ', atn.last_name)) as assigned_to_name, g.description as goal_name" +
                " from bd_1.tasks t " +
                " join bd_1.employees afn on t.fk_assigned_for = afn.document_id " +
                " join bd_1.employees atn on atn.document_id = t.fk_assigned_to " +
                " join bd_1.goals g on g.id = t.fk_goal " +
                " where t.id = :id";

        return jdbcTemplate.queryForObject(sql,map,new TaskDataRowMapper());
    }
}
