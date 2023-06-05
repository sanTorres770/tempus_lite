package com.santorres.tempus_lite.evidence.use_case;

import com.santorres.tempus_lite.evidence.domain.EvidenceRepository;
import org.springframework.stereotype.Service;

@Service
public class ApproveOrRefuseEvidenceUseCase {

    private final EvidenceRepository evidenceRepository;

    public ApproveOrRefuseEvidenceUseCase(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public void approveOrRefuseEvidence(String evidenceId, String selection, String observation){
        evidenceRepository.approveOrRefuseEvidence(evidenceId, selection, observation);
    }
}
