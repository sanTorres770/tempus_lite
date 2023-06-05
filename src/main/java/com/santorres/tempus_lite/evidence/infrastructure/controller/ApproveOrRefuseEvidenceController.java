package com.santorres.tempus_lite.evidence.infrastructure.controller;

import com.santorres.tempus_lite.evidence.use_case.ApproveOrRefuseEvidenceUseCase;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.GetUserByUserNameUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class ApproveOrRefuseEvidenceController {

    private final ApproveOrRefuseEvidenceUseCase approveOrRefuseEvidenceUseCase;
    private final TaskRepository taskRepository;
    private final GetUserByUserNameUseCase getUserByUserNameUseCase;


    public ApproveOrRefuseEvidenceController(ApproveOrRefuseEvidenceUseCase approveOrRefuseEvidenceUseCase, TaskRepository taskRepository, GetUserByUserNameUseCase getUserByUserNameUseCase) {
        this.approveOrRefuseEvidenceUseCase = approveOrRefuseEvidenceUseCase;
        this.taskRepository = taskRepository;
        this.getUserByUserNameUseCase = getUserByUserNameUseCase;
    }

    @PutMapping("/evidence/approve")
    public String approve(@RequestParam Map<String,String> body, RedirectAttributes flash, Authentication authentication){

        String username = authentication.getName();

        User user = getUserByUserNameUseCase.getUserByUserName(username);

        String employeeName = user.getName() + " " + user.getLastName();

        approveOrRefuseEvidenceUseCase.approveOrRefuseEvidence(body.get("evidenceId"), body.get("selection"), " // " + employeeName + ": " + body.get("observation"));


        if (body.get("selection").equals("A")){

            double progress = Double.parseDouble(body.get("progress"));

            taskRepository.updateTaskAndGoalProgress(body.get("fkTask"), progress);

            flash.addFlashAttribute("info","La evidencia fue aprobada correctamente.");

        }else {

            flash.addFlashAttribute("info","Se actualizó la evidencia a No aprobada.");

        }


        return "redirect:/task/get/"+body.get("fkTask");

    }

}
