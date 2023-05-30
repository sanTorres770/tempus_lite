package com.santorres.tempus_lite.evidence.use_case;

import com.santorres.tempus_lite.evidence.domain.EvidenceData;
import com.santorres.tempus_lite.evidence.domain.EvidenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetEvidencesByTaskUseCase {
    private final EvidenceRepository evidenceRepository;

    public GetEvidencesByTaskUseCase(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public List<EvidenceData> getEvidencesByTask(String fkTask){
        return evidenceRepository.getEvidencesByTask(fkTask);
    }
}
