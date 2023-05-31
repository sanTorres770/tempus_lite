package com.santorres.tempus_lite.workshift.infrastructure.persistence;

import com.santorres.tempus_lite.shared.domian.ConvertToLocalTime;
import com.santorres.tempus_lite.workshift.domain.Workshift;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

@Repository
public class WorkshiftRowMapper implements RowMapper<Workshift> {
    @Override
    public Workshift mapRow(ResultSet rs, int rowNum) throws SQLException {

        String id = rs.getString("id");
        LocalTime initialHour = ConvertToLocalTime.convert(rs.getTime("initial_hour"));
        LocalTime finalHour = ConvertToLocalTime.convert(rs.getTime("final_hour"));

        return new Workshift(
                id,
                initialHour,
                finalHour
        );
    }
}
