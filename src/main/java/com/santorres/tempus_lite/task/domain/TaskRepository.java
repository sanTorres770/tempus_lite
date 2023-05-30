package com.santorres.tempus_lite.task.domain;

import java.util.List;

public interface TaskRepository {
    List<TaskData> getTasksByGoal(String goalId);

    boolean saveNewTask(Task task);

    TaskData getTaskById(String id);
}
