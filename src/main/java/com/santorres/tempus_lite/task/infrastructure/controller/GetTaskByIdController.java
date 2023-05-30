package com.santorres.tempus_lite.task.infrastructure.controller;

import com.santorres.tempus_lite.task.domain.Task;
import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.use_case.GetTaskByIdUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetTaskByIdController {

    private final GetTaskByIdUseCase getTaskByIdUseCase;

    public GetTaskByIdController(GetTaskByIdUseCase getTaskByIdUseCase) {
        this.getTaskByIdUseCase = getTaskByIdUseCase;
    }

    @GetMapping("/task/get/{id}")
    public String get(Model model, @PathVariable String id){

        TaskData taskData = getTaskByIdUseCase.getTaskById(id);

        model.addAttribute("taskData",taskData);

        return "/task/task_data";

    }
}
