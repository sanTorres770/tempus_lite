package com.santorres.tempus_lite.task.use_case;

import com.santorres.tempus_lite.task.domain.Task;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
public class SaveNewTaskUseCase {

    private final TaskRepository taskRepository;

    public SaveNewTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean saveNewTask(Map<String ,String> data){

        Task task = createTask(data);

        return taskRepository.saveNewTask(task);
    }

    private Task createTask(Map<String, String> data) {
        return new Task(
                UUID.randomUUID().toString(),
                data.get("description"),
                LocalDate.parse(data.get("initialDate")),
                LocalDate.parse(data.get("finalDate")),
                0.0,
                false,
                data.get("observation"),
                data.get("fkAssignedFor"),
                data.get("fkGoal")
        );
    }
}
