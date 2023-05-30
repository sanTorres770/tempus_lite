package com.santorres.tempus_lite.evidence.use_case;

import com.santorres.tempus_lite.evidence.domain.EvidenceData;
import com.santorres.tempus_lite.evidence.domain.EvidenceRepository;
import org.springframework.stereotype.Service;

@Service
public class GetEvidenceByIdUseCase {

    private final EvidenceRepository evidenceRepository;

    public GetEvidenceByIdUseCase(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public EvidenceData getEvidenceById(String evidenceId){
        return evidenceRepository.getEvidenceById(evidenceId);
    }
}
