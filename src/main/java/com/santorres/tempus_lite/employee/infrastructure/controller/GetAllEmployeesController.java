package com.santorres.tempus_lite.employee.infrastructure.controller;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.use_case.GetAllEmployeesUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class GetAllEmployeesController {

    private final GetAllEmployeesUseCase getAllEmployeesUseCase;

    public GetAllEmployeesController(GetAllEmployeesUseCase getAllEmployeesUseCase) {
        this.getAllEmployeesUseCase = getAllEmployeesUseCase;
    }

    @GetMapping("/employee/all")
    public String get(Model model){

        List<EmployeeData> employeeDataList =  getAllEmployeesUseCase.getAllEmployees();

        model.addAttribute("employeeDataList",employeeDataList);

        return "/employee/employee_list";
    }
}
