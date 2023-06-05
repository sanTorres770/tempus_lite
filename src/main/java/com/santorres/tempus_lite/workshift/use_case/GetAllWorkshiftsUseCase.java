package com.santorres.tempus_lite.workshift.use_case;

import com.santorres.tempus_lite.workshift.domain.Workshift;
import com.santorres.tempus_lite.workshift.domain.WorkshiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllWorkshiftsUseCase {
    private final WorkshiftRepository workshiftRepository;

    public GetAllWorkshiftsUseCase(WorkshiftRepository workshiftRepository) {
        this.workshiftRepository = workshiftRepository;
    }

    public List<Workshift> getAll(){
        return workshiftRepository.getAll();
    }
}
