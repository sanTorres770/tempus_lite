package com.santorres.tempus_lite.task.infrastructure.persistence;

import com.santorres.tempus_lite.task.domain.Task;
import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

        String sql = " select t.*, upper(concat(afn.name, ' ', afn.last_name)) as assigned_for_name, null as assigned_at," +
                " g.description as goal_name" +
                " from bd_1.tasks t " +
                " join bd_1.employees afn on t.fk_assigned_for = afn.document_id " +
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

        String sql = " select t.*, upper(concat(afn.name, ' ', afn.last_name)) as assigned_for_name, null as assigned_at," +
                " g.description as goal_name" +
                " from bd_1.tasks t " +
                " join bd_1.employees afn on t.fk_assigned_for = afn.document_id " +
                " join bd_1.goals g on g.id = t.fk_goal " +
                " where t.id = :id";

        return jdbcTemplate.queryForObject(sql,map,new TaskDataRowMapper());
    }

    @Override
    public List<TaskData> getTasksByEmployee(String employeeId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("employeeId",employeeId);

        String sql = " select t.*, upper(concat(afn.name, ' ', afn.last_name)) as assigned_for_name, at.assigned_at," +
                " g.description as goal_name" +
                " from bd_1.tasks t " +
                " join bd_1.employees afn on t.fk_assigned_for = afn.document_id " +
                " join bd_1.goals g on g.id = t.fk_goal " +
                " left outer join bd_1.assigned_tasks at on fk_employee = :employeeId" +
                " where at.fk_employee = :employeeId and fk_task= t.id";

        return jdbcTemplate.query(sql,map,new TaskDataRowMapper());
    }

    @Override
    public void updateTaskAndGoalProgress(String fkTask, double progress) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("fkTask", fkTask);
        map.addValue("progress", progress);

        String sql = " update bd_1.tasks set progress=:progress, finished = :progress >= 100 where id=:fkTask";

        jdbcTemplate.update(sql,map);


        // obtener estado de la tarea para si se libera el operario o no

        String getTaskStateSql = " select finished from bd_1.tasks where id=:fkTask";

        boolean taskState = jdbcTemplate.queryForObject(getTaskStateSql,map,Boolean.class);


        if (taskState){

            // obtener el id del empleado a liberar

            String sqlEmployeeId = "select fk_employee from bd_1.assigned_tasks where fk_task= :fkTask";

            String employeeId = jdbcTemplate.queryForObject(sqlEmployeeId,map,String.class);


            // liberar al operario al terminar la tarea, creando un nuevo registro de asigancion

            assignTaskNullToEmployee(employeeId, null, null);


        }



        // obtener el id del objetivo

        String sqlGoalId = "select fk_goal from bd_1.tasks where id= :fkTask";

        String goalId = jdbcTemplate.queryForObject(sqlGoalId,map,String.class);

        map.addValue("goalId", goalId);


        // obtener la cantidad de tareas No finalizadas

        String sql3 = "select count(*) from bd_1.tasks " +
                " where fk_goal=:goalId and finished = false";

        // actulizar a terminado el objetivo si todas no hay tareas sin finalizar

        if (jdbcTemplate.queryForObject(sql3, map, Integer.class) == 0){

            String sql4 = "update bd_1.goals set finished = true where id=:goalId";

            jdbcTemplate.update(sql4,map);
        }



    }

    @Override
    public void assignTaskNullToEmployee(String idDocument, String taskId, LocalDateTime assignedAt) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("idDocument", idDocument);
        map.addValue("taskId", taskId);
        map.addValue("assignedAt", assignedAt);

        try {

            String sql = " insert into bd_1.assigned_tasks(fk_employee, fk_task, assigned_at) values (:idDocument, :taskId, :assignedAt)";

            jdbcTemplate.update(sql,map);

        }catch (Exception e){

            System.out.println(e.getMessage());

        }
    }
}
