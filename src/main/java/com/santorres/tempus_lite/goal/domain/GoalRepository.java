package com.santorres.tempus_lite.goal.domain;

import java.util.List;

public interface GoalRepository {
    List<GoalData> getGoalsByArea(String fkArea);
    List<GoalData> getAllGoals();

    boolean saveNewGoal(Goal goal);

    GoalData getGoalById(String id);
}
