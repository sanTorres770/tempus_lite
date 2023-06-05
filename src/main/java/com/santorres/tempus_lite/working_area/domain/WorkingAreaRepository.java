package com.santorres.tempus_lite.working_area.domain;

import java.util.List;

public interface WorkingAreaRepository {
    boolean saveNewWorkingArea(WorkingArea workingArea);

    List<WorkingAreaData> getAll();

    WorkingAreaData getWorkingAreaByHead(String headAreaId);
}
