package com.santorres.tempus_lite.evidence.domain;

import java.util.List;

public interface EvidenceRepository {
    boolean saveNewEvidence(Evidence evidence);

    List<EvidenceData> getEvidencesByTask(String fkTask);

    EvidenceData getEvidenceById(String evidenceId);

    void approveOrRefuseEvidence(String evidenceId, String selection, String observation);
}
