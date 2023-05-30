package com.santorres.tempus_lite.task.use_case;

import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTasksByEmployeeUseCase {

    private final TaskRepository taskRepository;

    public GetTasksByEmployeeUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskData> getTasksByEmployee(String employeeId){
        return taskRepository.getTasksByEmployee(employeeId);
    }
}
