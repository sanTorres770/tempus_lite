package com.santorres.tempus_lite.goal.infrastructure.persistence;

import com.santorres.tempus_lite.goal.domain.GoalData;
import com.santorres.tempus_lite.shared.domian.ConvertToDate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Repository
public class GoalDataRowMapper implements RowMapper<GoalData> {
    @Override
    public GoalData mapRow(ResultSet rs, int rowNum) throws SQLException {

        String id = rs.getString("id");
        String description = rs.getString("description");
        LocalDate initialDate = ConvertToDate.convert(rs.getDate("initial_date"));
        LocalDate finalDate = ConvertToDate.convert(rs.getDate("final_date"));
        boolean finished = rs.getBoolean("finished");
        String observation = rs.getString("observation");
        String fkCreatedBy = rs.getString("fk_created_by");
        String fkArea = rs.getString("fk_area");
        String createdByName = rs.getString("created_by_name");
        String areaName = rs.getString("area_name");

        return new GoalData(
                id,
                description,
                initialDate,
                finalDate,
                finished,
                observation,
                fkCreatedBy,
                fkArea,
                createdByName,
                areaName
        );
    }
}
