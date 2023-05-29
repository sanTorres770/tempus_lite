package com.santorres.tempus_lite.employee.infrastructure.controller;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.use_case.GetEmployeeByIdUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GetEmployeeByIdController {

    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;

    public GetEmployeeByIdController(GetEmployeeByIdUseCase getEmployeeByIdUseCase) {
        this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
    }


    @GetMapping("employee/get/{documentId}")
    public EmployeeData getEmployee(@PathVariable String documentId){
        return getEmployeeByIdUseCase.getEmployeeById(documentId);
    }
}
