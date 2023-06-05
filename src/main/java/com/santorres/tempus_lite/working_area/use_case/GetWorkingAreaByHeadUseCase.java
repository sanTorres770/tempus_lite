package com.santorres.tempus_lite.working_area.use_case;

import com.santorres.tempus_lite.working_area.domain.WorkingArea;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaData;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaRepository;
import org.springframework.stereotype.Service;

@Service
public class GetWorkingAreaByHeadUseCase {

    private final WorkingAreaRepository workingAreaRepository;

    public GetWorkingAreaByHeadUseCase(WorkingAreaRepository workingAreaRepository) {
        this.workingAreaRepository = workingAreaRepository;
    }

    public WorkingAreaData getWorkingAreaByHead(String headAreaId){
        return workingAreaRepository.getWorkingAreaByHead(headAreaId);
    }
}
