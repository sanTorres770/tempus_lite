package com.santorres.tempus_lite.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
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
    private String fkAssignedTo;
    private String fkGoal;
    private String assignedForName;
    private String assignedToName;
    private String goalName;
}
