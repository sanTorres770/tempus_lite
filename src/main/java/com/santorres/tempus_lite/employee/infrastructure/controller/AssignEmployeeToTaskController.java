package com.santorres.tempus_lite.employee.infrastructure.controller;

import com.santorres.tempus_lite.employee.use_case.AssignEmployeeToTaskUseCase;
import com.santorres.tempus_lite.employee.use_case.GetOperatorEmployeesUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AssignEmployeeToTaskController {

    private final AssignEmployeeToTaskUseCase assignEmployeeToTaskUseCase;

    public AssignEmployeeToTaskController(AssignEmployeeToTaskUseCase assignEmployeeToTaskUseCase, GetOperatorEmployeesUseCase getOperatorEmployeesUseCase) {
        this.assignEmployeeToTaskUseCase = assignEmployeeToTaskUseCase;
    }


    @PutMapping("/employee/assign/task/save")
    public String save(@RequestParam String employeeId, @RequestParam String fkTask, @RequestParam String fkArea,
                       Model model, RedirectAttributes flash){

        if (assignEmployeeToTaskUseCase.assignEmployeeToTask(employeeId,fkTask)){
            flash.addFlashAttribute("success","El operario se asignó correctamente a esta tarea.");
        }else {
            flash.addFlashAttribute("error","No se pudo asignar al empleado correctamente.");
        }

        return "redirect:/task/employees/by/"+fkTask+"/"+fkArea;

    }
    

}
