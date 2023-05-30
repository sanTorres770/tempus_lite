package com.santorres.tempus_lite.goal.use_case;

import com.santorres.tempus_lite.goal.domain.GoalData;
import com.santorres.tempus_lite.goal.domain.GoalRepository;
import org.springframework.stereotype.Service;

@Service
public class GetGoalByIdUseCase {

    private final GoalRepository goalRepository;

    public GetGoalByIdUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public GoalData getGoalById(String id){
        return goalRepository.getGoalById(id);
    }
}
