package com.santorres.tempus_lite.goal.use_case;

import com.santorres.tempus_lite.goal.domain.GoalData;
import com.santorres.tempus_lite.goal.domain.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGoalsByAreaUseCase {
    private final GoalRepository goalRepository;

    public GetGoalsByAreaUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<GoalData> getGoalsByArea(String fkArea){
        return goalRepository.getGoalsByArea(fkArea);
    }
}
