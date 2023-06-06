package com.santorres.tempus_lite.task.use_case;

import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetTaskListByDateUseCase {
    private final TaskRepository taskRepository;

    public GetTaskListByDateUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskData> getTaskListByDate(LocalDate initialDate, LocalDate finalDate){
        return taskRepository.getTaskListByDate(initialDate,finalDate);
    }
}
