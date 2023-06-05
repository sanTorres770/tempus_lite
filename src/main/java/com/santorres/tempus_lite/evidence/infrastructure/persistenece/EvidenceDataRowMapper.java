package com.santorres.tempus_lite.evidence.infrastructure.persistenece;

import com.santorres.tempus_lite.evidence.domain.EvidenceData;
import com.santorres.tempus_lite.shared.domian.ConvertToLocalDateTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EvidenceDataRowMapper implements RowMapper<EvidenceData> {
    @Override
    public EvidenceData mapRow(ResultSet rs, int rowNum) throws SQLException {

        String id = rs.getString("id");
        String fileName = rs.getString("file_name");
        LocalDateTime uploadedAt = ConvertToLocalDateTime.convert(rs.getTimestamp("uploaded_at"));
        String observation = rs.getString("observation");
        double progress = rs.getDouble("progress");
        String fkTask = rs.getString("fk_task");
        String taskDescription = rs.getString("task_description");
        String approved = rs.getString("approved");
        String createdBy = rs.getString("created_by");
        String createdByName = rs.getString("created_by_name");


        return new EvidenceData(
                id,
                fileName,
                uploadedAt,
                observation,
                progress,
                fkTask,
                taskDescription,
                approved,
                createdBy,
                createdByName
        );
    }
}
