package com.santorres.tempus_lite.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskData {

    private String id;
    private String description;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private double progress;
    private boolean finished;
    private String observation;
    private String fkAssignedFor;
    private String fkGoal;
    private String assignedForName;
    private String goalName;
    private LocalDateTime assignedAt;
    private String assignedToName;
}
