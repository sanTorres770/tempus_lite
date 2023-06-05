package com.santorres.tempus_lite.working_area.infrastructure.controller;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.use_case.GetOperatorEmployeesUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GetEmployeesListByAreaController {

    public final GetOperatorEmployeesUseCase getOperatorEmployeesUseCase;

    public GetEmployeesListByAreaController(GetOperatorEmployeesUseCase getOperatorEmployeesUseCase) {
        this.getOperatorEmployeesUseCase = getOperatorEmployeesUseCase;
    }

    @GetMapping("/area/employees/by/{fkArea}")
    public String getList(Model model, @PathVariable String fkArea){

        List<EmployeeData> employeeDataList = getOperatorEmployeesUseCase.getOperatorEmployeesByArea(fkArea);

        model.addAttribute("employeeDataList",employeeDataList);

        return "area/employee_by_area_list";

    }

    @ModelAttribute("operators")
    public List<EmployeeData> getOperatorsAvailable(){
        return getOperatorEmployeesUseCase.getOperatorEmployeesAvailable();
    }
}
