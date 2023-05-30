package com.santorres.tempus_lite.evidence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Evidence {
    private String id;
    private String fileName;
    private LocalDateTime uploadedAt;
    private String observation;
    private double progress;
    private String fkTask;
    private String approved;
}
