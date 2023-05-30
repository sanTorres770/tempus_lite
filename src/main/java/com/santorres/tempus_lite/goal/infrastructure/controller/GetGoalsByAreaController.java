package com.santorres.tempus_lite.goal.infrastructure.controller;

import com.santorres.tempus_lite.goal.domain.GoalData;
import com.santorres.tempus_lite.goal.use_case.GetGoalsByAreaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GetGoalsByAreaController {

    private final GetGoalsByAreaUseCase getGoalsByAreaUseCase;

    public GetGoalsByAreaController(GetGoalsByAreaUseCase getGoalsByAreaUseCase) {
        this.getGoalsByAreaUseCase = getGoalsByAreaUseCase;
    }

    @GetMapping("/goal/by/{fkArea}")
    public String get(@PathVariable String fkArea, Model model){

        List<GoalData> goalDataList = getGoalsByAreaUseCase.getGoalsByArea(fkArea);

        model.addAttribute("goalDataList",goalDataList);
        model.addAttribute("fkArea",fkArea);

        return "/goal/goal_list";
    }

}
