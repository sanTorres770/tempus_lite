package com.santorres.tempus_lite.evidence.infrastructure.controller;

import com.santorres.tempus_lite.evidence.use_case.ApproveOrRefuseEvidenceUseCase;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApproveOrRefuseEvidenceController {

    private final ApproveOrRefuseEvidenceUseCase approveOrRefuseEvidenceUseCase;
    private final TaskRepository taskRepository;

    public ApproveOrRefuseEvidenceController(ApproveOrRefuseEvidenceUseCase approveOrRefuseEvidenceUseCase, TaskRepository taskRepository) {
        this.approveOrRefuseEvidenceUseCase = approveOrRefuseEvidenceUseCase;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/evidence/approve/{fkTask}/{selection}/{evidenceId}")
    public String approve(@PathVariable String selection, @PathVariable String fkTask,
                          @PathVariable String evidenceId, RedirectAttributes flash){

        String selectionFinal = selection.split(":")[0];

        approveOrRefuseEvidenceUseCase.approveOrRefuseEvidence(evidenceId,selectionFinal);


        if (selectionFinal.equals("A")){

            double progress = Double.parseDouble(selection.split(":")[1]);

            taskRepository.updateTaskProgress(fkTask,progress);

            flash.addFlashAttribute("info","La evidencia fue aprobada correctamente.");

        }else {

            flash.addFlashAttribute("info","Se actualizó la evidencia a No aprobada.");

        }


        return "redirect:/task/get/"+fkTask;

    }

}
