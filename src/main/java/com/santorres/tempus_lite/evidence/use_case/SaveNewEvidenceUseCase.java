package com.santorres.tempus_lite.evidence.use_case;

import com.santorres.tempus_lite.evidence.domain.Evidence;
import com.santorres.tempus_lite.evidence.domain.EvidenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class SaveNewEvidenceUseCase {

    private final EvidenceRepository evidenceRepository;

    public SaveNewEvidenceUseCase(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public boolean saveNewEvidence(Map<String,String> data){

        Evidence evidence = createEvidence(data);

        return evidenceRepository.saveNewEvidence(evidence);
    }

    private Evidence createEvidence(Map<String, String> data) {
        return new Evidence(
                UUID.randomUUID().toString(),
                data.get("dataImage"),
                LocalDateTime.now(),
                data.get("employeeName") + ": " + data.get("observation"),
                Double.parseDouble(data.get("progress")),
                data.get("fkTask"),
                "P",
                data.get("createdBy")
        );
    }
}
