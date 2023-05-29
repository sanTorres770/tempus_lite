package com.santorres.tempus_lite.goal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Goal {

    private String id;
    private String description;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private boolean finished;
    private String observation;
    private String fkCreatedBy;
    private String fkArea;
}
