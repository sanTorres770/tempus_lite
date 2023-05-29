package com.santorres.tempus_lite.working_area.infrastructure.persistence;


import com.santorres.tempus_lite.working_area.domain.WorkingArea;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingAreaDataRowMapper implements RowMapper<WorkingAreaData> {
    @Override
    public WorkingAreaData mapRow(ResultSet rs, int rowNum) throws SQLException {

        String id = rs.getString("id");
        String name = rs.getString("name");
        String fkAreaBoss = rs.getString("fk_area_boss");
        String bossName = rs.getString("boss_name");

        return new WorkingAreaData(
                id,
                name,
                fkAreaBoss,
                bossName
        );
    }
}
