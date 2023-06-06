package com.santorres.tempus_lite.task.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository {
    List<TaskData> getTasksByGoal(String goalId);

    boolean saveNewTask(Task task);

    TaskData getTaskById(String id);

    List<TaskData> getTasksByEmployee(String employeeId);

    void updateTaskAndGoalProgress(String fkTask, double progress);

    void assignTaskNullToEmployee(String idDocument, String taskId, LocalDateTime assignedAt);

    List<TaskData> getTaskListByDate(LocalDate initialDate, LocalDate finalDate);

    int getNumberOfNotFinishedTasks();
}
