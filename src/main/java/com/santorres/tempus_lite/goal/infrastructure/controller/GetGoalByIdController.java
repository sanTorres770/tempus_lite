package com.santorres.tempus_lite.goal.infrastructure.controller;

import com.santorres.tempus_lite.goal.domain.GoalData;
import com.santorres.tempus_lite.goal.use_case.GetGoalByIdUseCase;
import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.use_case.GetTasksByGoalUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GetGoalByIdController {

    private final GetGoalByIdUseCase getGoalByIdUseCase;
    private final GetTasksByGoalUseCase getTasksByGoalUseCase;

    public GetGoalByIdController(GetGoalByIdUseCase getGoalByIdUseCase, GetTasksByGoalUseCase getTasksByGoalUseCase) {
        this.getGoalByIdUseCase = getGoalByIdUseCase;
        this.getTasksByGoalUseCase = getTasksByGoalUseCase;
    }

    @GetMapping("/goal/get/{id}")
    public String get(Model model, @PathVariable String id){

        GoalData goalData = getGoalByIdUseCase.getGoalById(id);

        List<TaskData> taskDataList = getTasksByGoalUseCase.getTasksByGoal(id);

        model.addAttribute("goalData",goalData);
        model.addAttribute("taskDataList",taskDataList);
        model.addAttribute("fkGoal",id);

        return "/goal/goal_data";
    }
}
