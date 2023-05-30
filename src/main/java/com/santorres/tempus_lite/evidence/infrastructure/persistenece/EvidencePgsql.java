package com.santorres.tempus_lite.evidence.infrastructure.persistenece;

import com.santorres.tempus_lite.evidence.domain.Evidence;
import com.santorres.tempus_lite.evidence.domain.EvidenceData;
import com.santorres.tempus_lite.evidence.domain.EvidenceRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EvidencePgsql implements EvidenceRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EvidencePgsql(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean saveNewEvidence(Evidence evidence) {

        try {

            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("id",evidence.getId());
            map.addValue("fileName",evidence.getFileName());
            map.addValue("uploadedAt",evidence.getUploadedAt());
            map.addValue("observation",evidence.getObservation());
            map.addValue("progress",evidence.getProgress());
            map.addValue("fkTask",evidence.getFkTask());
            map.addValue("approved",evidence.getApproved());

            String sql = "insert into bd_1.evidences values (" +
                    " :id," +
                    " :fileName," +
                    " :uploadedAt," +
                    " :observation," +
                    " :fkTask," +
                    " :progress," +
                    " :approved)";

            return jdbcTemplate.update(sql,map) > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<EvidenceData> getEvidencesByTask(String fkTask) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("fkTask",fkTask);

        String sql = "select e.*, t.description as task_description from bd_1.evidences e " +
                " join bd_1.tasks t on t.id = e.fk_task " +
                " where fk_task = :fkTask";

        return jdbcTemplate.query(sql,map, new EvidenceDataRowMapper());
    }

    @Override
    public EvidenceData getEvidenceById(String evidenceId) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("evidenceId",evidenceId);

        String sql = "select e.*, t.description as task_description from bd_1.evidences e " +
                " join bd_1.tasks t on t.id = e.fk_task " +
                " where e.id = :evidenceId";

        return jdbcTemplate.queryForObject(sql,map, new EvidenceDataRowMapper());
    }

    @Override
    public void approveOrRefuseEvidence(String evidenceId, String selection) {

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("evidenceId",evidenceId);
        map.addValue("selection",selection);

        String sql = " update bd_1.evidences set approved = :selection where id=:evidenceId ";

        jdbcTemplate.update(sql,map);

    }
}
