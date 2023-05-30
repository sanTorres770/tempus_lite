package com.santorres.tempus_lite.task.infrastructure.controller;

import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.use_case.GetTasksByEmployeeUseCase;
import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.GetUserByUserNameUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetTasksByEmployeeController {

    private final GetTasksByEmployeeUseCase getTasksByEmployeeUseCase;
    private final GetUserByUserNameUseCase getUserByUserNameUseCase;

    public GetTasksByEmployeeController(GetTasksByEmployeeUseCase getTasksByEmployeeUseCase, GetUserByUserNameUseCase getUserByUserNameUseCase) {
        this.getTasksByEmployeeUseCase = getTasksByEmployeeUseCase;
        this.getUserByUserNameUseCase = getUserByUserNameUseCase;
    }

    @GetMapping("/task/by")
    public String get(Model model, Authentication authentication){

        String username = authentication.getName();

        User user = getUserByUserNameUseCase.getUserByUserName(username);

        List<TaskData> taskDataList = getTasksByEmployeeUseCase.getTasksByEmployee(user.getId());

        model.addAttribute("taskDataList",taskDataList);
        model.addAttribute("user",user.getName()+" "+user.getLastName());

        return "task/task_list_by_employee";
    }

}
