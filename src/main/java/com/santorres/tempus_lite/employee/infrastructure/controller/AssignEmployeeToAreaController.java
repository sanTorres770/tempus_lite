package com.santorres.tempus_lite.employee.infrastructure.controller;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.use_case.AssignEmployeeToAreaUseCase;
import com.santorres.tempus_lite.employee.use_case.GetOperatorEmployeesUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AssignEmployeeToAreaController {

    private final AssignEmployeeToAreaUseCase assignEmployeeToAreaUseCase;

    public AssignEmployeeToAreaController(AssignEmployeeToAreaUseCase assignEmployeeToAreaUseCase, GetOperatorEmployeesUseCase getOperatorEmployeesUseCase) {
        this.assignEmployeeToAreaUseCase = assignEmployeeToAreaUseCase;
    }


    @PutMapping("/employee/assign/area/save")
    public String save(@RequestParam String employeeId, @RequestParam String fkArea,
                       Model model, RedirectAttributes flash){

        if (assignEmployeeToAreaUseCase.assignEmployeeToArea(employeeId,fkArea)){
            flash.addFlashAttribute("success","El empleado se asignó correctamente a esta área de trabajo.");
        }else {
            flash.addFlashAttribute("error","No se pudo asignar al empleado correctamente.");
        }

        return "redirect:/area/employees/by/"+fkArea;

    }
    

}
