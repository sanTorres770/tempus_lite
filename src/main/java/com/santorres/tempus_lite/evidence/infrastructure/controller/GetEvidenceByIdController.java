package com.santorres.tempus_lite.evidence.infrastructure.controller;

import com.santorres.tempus_lite.evidence.domain.EvidenceData;
import com.santorres.tempus_lite.evidence.use_case.GetEvidenceByIdUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetEvidenceByIdController {
    private final GetEvidenceByIdUseCase getEvidenceByIdUseCase;

    public GetEvidenceByIdController(GetEvidenceByIdUseCase getEvidenceByIdUseCase) {
        this.getEvidenceByIdUseCase = getEvidenceByIdUseCase;
    }

    @GetMapping("/evidence/get/{id}")
    public String get(Model model, @PathVariable String id){

        EvidenceData evidenceData = getEvidenceByIdUseCase.getEvidenceById(id);

        model.addAttribute("evidenceData",evidenceData);

        return "evidence/evidence_data";

    }
}
