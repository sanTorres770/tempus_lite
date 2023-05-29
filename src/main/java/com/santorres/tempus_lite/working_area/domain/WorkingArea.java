package com.santorres.tempus_lite.working_area.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkingArea {
    private String id;
    private String name;
    private String fkAreaBoss;
}
