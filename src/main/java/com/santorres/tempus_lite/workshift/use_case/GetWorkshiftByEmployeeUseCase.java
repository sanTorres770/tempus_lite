package com.santorres.tempus_lite.workshift.use_case;

import com.santorres.tempus_lite.workshift.domain.Workshift;
import com.santorres.tempus_lite.workshift.domain.WorkshiftRepository;
import org.springframework.stereotype.Service;

@Service
public class GetWorkshiftByEmployeeUseCase {

    private final WorkshiftRepository workshiftRepository;

    public GetWorkshiftByEmployeeUseCase(WorkshiftRepository workshiftRepository) {
        this.workshiftRepository = workshiftRepository;
    }

    public Workshift getWorkshiftByEmployee(String employeeId){
        return workshiftRepository.getWorkshiftByEmployee(employeeId);
}
}
