package com.santorres.tempus_lite.task.use_case;

import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTasksByGoalUseCase {

    private final TaskRepository taskRepository;

    public GetTasksByGoalUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskData> getTasksByGoal(String goalId){
        return taskRepository.getTasksByGoal(goalId);
    }
}
