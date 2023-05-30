package com.santorres.tempus_lite.task.infrastructure.controller;

import com.santorres.tempus_lite.evidence.domain.EvidenceData;
import com.santorres.tempus_lite.evidence.use_case.GetEvidencesByTaskUseCase;
import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.use_case.GetTaskByIdUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GetTaskByIdController {

    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final GetEvidencesByTaskUseCase getEvidencesByTaskUseCase;

    public GetTaskByIdController(GetTaskByIdUseCase getTaskByIdUseCase, GetEvidencesByTaskUseCase getEvidencesByTaskUseCase) {
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.getEvidencesByTaskUseCase = getEvidencesByTaskUseCase;
    }

    @GetMapping("/task/get/{id}")
    public String get(Model model, @PathVariable String id){

        TaskData taskData = getTaskByIdUseCase.getTaskById(id);

        List<EvidenceData> evidenceDataList = getEvidencesByTaskUseCase.getEvidencesByTask(id);

        model.addAttribute("taskData",taskData);
        model.addAttribute("evidenceDataList",evidenceDataList);

        return "/task/task_data";

    }
}
