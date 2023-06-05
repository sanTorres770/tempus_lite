package com.santorres.tempus_lite.task.infrastructure.controller;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.use_case.GetOperatorEmployeesUseCase;
import com.santorres.tempus_lite.task.use_case.SaveNewTaskUseCase;
import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.GetUserByUserNameUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class SaveNewTaskController {

    private final SaveNewTaskUseCase saveNewTaskUseCase;
    private final GetOperatorEmployeesUseCase operatorEmployeesUseCase;
    private final GetUserByUserNameUseCase getUserByUserNameUseCase;

    public SaveNewTaskController(SaveNewTaskUseCase saveNewTaskUseCase, GetOperatorEmployeesUseCase operatorEmployeesUseCase, GetUserByUserNameUseCase getUserByUserNameUseCase) {
        this.saveNewTaskUseCase = saveNewTaskUseCase;
        this.operatorEmployeesUseCase = operatorEmployeesUseCase;
        this.getUserByUserNameUseCase = getUserByUserNameUseCase;
    }

    @GetMapping("/task/form/{fkGoal}/{fkArea}")
    public String form(Model model, @PathVariable String fkGoal, @PathVariable String fkArea, Authentication authentication){

        String username = authentication.getName();

        User user = getUserByUserNameUseCase.getUserByUserName(username);

        model.addAttribute("fkGoal",fkGoal);
        model.addAttribute("assignedFor",user.getId());

        return "/task/create_task_form";
    }

    @PostMapping("/task/save")
    public String save(@RequestParam Map<String, String> body, RedirectAttributes flash){

        if (saveNewTaskUseCase.saveNewTask(body)){
            flash.addFlashAttribute("success","La tarea se guardó correctamente!");
        }else {
            flash.addFlashAttribute("error","La información no se guardó. Revise y vuelva a intentarlo");
        }

        return "redirect:/goal/get/" + body.get("fkGoal");

    }

}
