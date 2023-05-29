package com.santorres.tempus_lite.employee.infrastructure.controller;

import com.santorres.tempus_lite.employee.use_case.UpdateEmployeeUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateEmployeeController {

    private final UpdateEmployeeUseCase updateEmployeeUseCase;

    public UpdateEmployeeController(UpdateEmployeeUseCase updateEmployeeUseCase) {
        this.updateEmployeeUseCase = updateEmployeeUseCase;
    }

    @PutMapping("/employee/{documentId}")
    public boolean updateEmployee(@PathVariable String documentId, @RequestBody Map<String,String> body){
        return updateEmployeeUseCase.updateEmployee(body, documentId);
    }
}
