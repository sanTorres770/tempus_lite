package com.santorres.tempus_lite.task.infrastructure.persistence;

import com.santorres.tempus_lite.shared.domian.ConvertToDate;
import com.santorres.tempus_lite.task.domain.TaskData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TaskDataRowMapper implements RowMapper<TaskData> {
    @Override
    public TaskData mapRow(ResultSet rs, int rowNum) throws SQLException {


        String id = rs.getString("id");
        String description = rs.getString("description");
        LocalDate initialDate = ConvertToDate.convert(rs.getDate("initial_date"));
        LocalDate finalDate = ConvertToDate.convert(rs.getDate("final_date"));
        double progress = rs.getDouble("progress");
        boolean finished = rs.getBoolean("finished");
        String observation = rs.getString("observation");
        String fkAssignedFor = rs.getString("fk_assigned_for");
        String fkAssignedTo = rs.getString("fk_assigned_to");
        String fkGoal = rs.getString("fk_goal");
        String assignedForName = rs.getString("assigned_for_name");
        String assignedToName = rs.getString("assigned_to_name");
        String goalName = rs.getString("goal_name");


        return new TaskData(
                id,
                description,
                initialDate,
                finalDate,
                progress,
                finished,
                observation,
                fkAssignedFor,
                fkAssignedTo,
                fkGoal,
                assignedForName,
                assignedToName,
                goalName
        );
    }
}
