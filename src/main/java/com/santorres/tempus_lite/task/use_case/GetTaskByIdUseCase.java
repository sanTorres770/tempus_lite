package com.santorres.tempus_lite.task.use_case;

import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {
    private final TaskRepository taskRepository;

    public GetTaskByIdUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskData getTaskById(String id){
        return taskRepository.getTaskById(id);
    }
}
