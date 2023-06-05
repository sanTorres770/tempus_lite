package com.santorres.tempus_lite.task.infrastructure.controller;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.use_case.GetOperatorEmployeesUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GetEmployeesListByTaskController {

    public final GetOperatorEmployeesUseCase getOperatorEmployeesUseCase;

    public GetEmployeesListByTaskController(GetOperatorEmployeesUseCase getOperatorEmployeesUseCase) {
        this.getOperatorEmployeesUseCase = getOperatorEmployeesUseCase;
    }

    @GetMapping("/task/employees/by/{fkTask}/{fkArea}")
    public String getList(Model model, @PathVariable String fkTask, @PathVariable String fkArea){

        List<EmployeeData> employeeDataList = getOperatorEmployeesUseCase.getOperatorEmployeesByTask(fkTask);

        List<EmployeeData> operators = getOperatorEmployeesUseCase.getOperatorEmployeesAvailableByArea(fkArea);

        model.addAttribute("employeeDataList",employeeDataList);
        model.addAttribute("operators",operators);
        model.addAttribute("fkArea",fkArea);

        return "task/employee_by_task_list";

    }


}
