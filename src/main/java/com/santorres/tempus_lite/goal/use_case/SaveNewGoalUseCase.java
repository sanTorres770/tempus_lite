package com.santorres.tempus_lite.goal.use_case;

import com.santorres.tempus_lite.goal.domain.Goal;
import com.santorres.tempus_lite.goal.domain.GoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
public class SaveNewGoalUseCase {
    private final GoalRepository goalRepository;

    public SaveNewGoalUseCase(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public boolean saveNewGoal(Map<String, String> data){

        Goal goal = createGoal(data);


        return goalRepository.saveNewGoal(goal);
    }

    private Goal createGoal(Map<String, String> data) {

        return new Goal(
                UUID.randomUUID().toString(),
                data.get("description"),
                LocalDate.parse(data.get("initialDate")),
                LocalDate.parse(data.get("finalDate")),
                false,
                data.get("observation"),
                data.get("fkArea"),
                data.get("createdBy")
        );
    }
}
