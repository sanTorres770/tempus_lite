package com.santorres.tempus_lite.employee_user_form.controller;

import com.santorres.tempus_lite.employee_role.domain.EmployeeRole;
import com.santorres.tempus_lite.employee_role.use_case.GetAllEmployeeRolesUseCase;
import com.santorres.tempus_lite.employee_user_form.use_case.SaveFormDataEmployeeUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class FormDataEmployeeUserController {

    private final SaveFormDataEmployeeUseCase saveFormDataEmployeeUseCase;
    private final GetAllEmployeeRolesUseCase getAllEmployeeRolesUseCase;

    public FormDataEmployeeUserController(SaveFormDataEmployeeUseCase saveFormDataEmployeeUseCase, GetAllEmployeeRolesUseCase getAllEmployeeRolesUseCase) {
        this.saveFormDataEmployeeUseCase = saveFormDataEmployeeUseCase;
        this.getAllEmployeeRolesUseCase = getAllEmployeeRolesUseCase;
    }

    @GetMapping("/employee/form")
    public String form(Model model){

        return "employee/create_employee_form";
    }

    @PostMapping("/employee/save")
    public String save(@RequestParam Map<String, String> body, RedirectAttributes flash) {

        if (saveFormDataEmployeeUseCase.saveFormData(body)){

            flash.addFlashAttribute("success","La información del empleado se guardó correctamente!");
        }else {

            flash.addFlashAttribute("error","La información no se guardó. Revise y vuelva a intentar.");
        }

        return "redirect:/employee/all";

    }

    @ModelAttribute("roles")
    public List<EmployeeRole> getRoles(){
        return getAllEmployeeRolesUseCase.getAll();
    }
}