package com.santorres.tempus_lite.task.infrastructure.persistence;

import com.santorres.tempus_lite.shared.domian.ConvertToDate;
import com.santorres.tempus_lite.shared.domian.ConvertToLocalDateTime;
import com.santorres.tempus_lite.task.domain.TaskData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        String fkGoal = rs.getString("fk_goal");
        String assignedForName = rs.getString("assigned_for_name");
        String goalName = rs.getString("goal_name");
        LocalDateTime assignedAt = ConvertToLocalDateTime.convert(rs.getTimestamp("assigned_at"));
        String assignedToName = rs.getString("assigned_to_name");


        return new TaskData(
                id,
                description,
                initialDate,
                finalDate,
                progress,
                finished,
                observation,
                fkAssignedFor,
                fkGoal,
                assignedForName,
                goalName,
                assignedAt,
                assignedToName
        );
    }
}
