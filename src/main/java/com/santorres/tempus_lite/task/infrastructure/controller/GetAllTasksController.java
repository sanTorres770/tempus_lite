package com.santorres.tempus_lite.task.infrastructure.controller;

import com.santorres.tempus_lite.task.use_case.GetTasksByGoalUseCase;
import org.springframework.stereotype.Controller;

@Controller
public class GetAllTasksController {

    private final GetTasksByGoalUseCase getTasksByGoalUseCase;

    public GetAllTasksController(GetTasksByGoalUseCase getTasksByGoalUseCase) {
        this.getTasksByGoalUseCase = getTasksByGoalUseCase;
    }

    /*@GetMapping("/task/all")
    public */
}
