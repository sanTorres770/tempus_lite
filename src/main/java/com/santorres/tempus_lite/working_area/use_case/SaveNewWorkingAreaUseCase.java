package com.santorres.tempus_lite.working_area.use_case;

import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import com.santorres.tempus_lite.working_area.domain.WorkingArea;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.UUID;

@Service
public class SaveNewWorkingAreaUseCase {

    private final WorkingAreaRepository workingAreaRepository;
    private final EmployeeRepository employeeRepository;

    public SaveNewWorkingAreaUseCase(WorkingAreaRepository workingAreaRepository, EmployeeRepository employeeRepository) {
        this.workingAreaRepository = workingAreaRepository;
        this.employeeRepository = employeeRepository;
    }

    public boolean saveNewWorkingArea(@RequestParam Map<String,String> data){

        WorkingArea workingArea = createWorkingArea(data);

        if(workingAreaRepository.saveNewWorkingArea(workingArea)){
            employeeRepository.updateEmployeeArea(workingArea.getFkAreaBoss(),workingArea.getId());
            return true;
        }

        return false;
    }

    private WorkingArea createWorkingArea(Map<String, String> data) {

        return new WorkingArea(
                UUID.randomUUID().toString(),
                data.get("name"),
                data.get("fkAreaBoss")
        );
    }
}
