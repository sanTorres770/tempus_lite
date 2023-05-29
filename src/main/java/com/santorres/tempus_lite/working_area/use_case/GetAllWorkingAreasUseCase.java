package com.santorres.tempus_lite.working_area.use_case;

import com.santorres.tempus_lite.working_area.domain.WorkingArea;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaData;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllWorkingAreasUseCase {

    private final WorkingAreaRepository workingAreaRepository;

    public GetAllWorkingAreasUseCase(WorkingAreaRepository workingAreaRepository) {
        this.workingAreaRepository = workingAreaRepository;
    }

    public List<WorkingAreaData> getAll(){
        return workingAreaRepository.getAll();
    }
}
