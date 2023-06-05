package com.santorres.tempus_lite.working_area.infrastructure.controller;

import com.santorres.tempus_lite.employee.domain.EmployeeData;
import com.santorres.tempus_lite.employee.domain.EmployeeRepository;
import com.santorres.tempus_lite.employee.use_case.GetHeadAreaEmployeesUseCase;
import com.santorres.tempus_lite.working_area.use_case.SaveNewWorkingAreaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class SaveNewWorkingAreaController {

    private final GetHeadAreaEmployeesUseCase getHeadAreaEmployeesUseCase;
    private final SaveNewWorkingAreaUseCase saveNewWorkingAreaUseCase;
    private final EmployeeRepository employeeRepository;

    public SaveNewWorkingAreaController(GetHeadAreaEmployeesUseCase getHeadAreaEmployeesUseCase, SaveNewWorkingAreaUseCase saveNewWorkingAreaUseCase, EmployeeRepository employeeRepository) {
        this.getHeadAreaEmployeesUseCase = getHeadAreaEmployeesUseCase;
        this.saveNewWorkingAreaUseCase = saveNewWorkingAreaUseCase;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/area/form")
    public String form(Model model){

        return "area/create_area_form";
    }

    @PostMapping("/area/save")
    public String save(@RequestParam Map<String,String> body, RedirectAttributes flash){

        if (saveNewWorkingAreaUseCase.saveNewWorkingArea(body)){

            flash.addFlashAttribute("success",String.format("El área de trabajo %s se guardó correctamente!",body.get("name")));
        }else {

            flash.addFlashAttribute("error","La información no se guardó. Revise y vuelva a intentar.");
        }

        return "redirect:/area/all";
    }



    @ModelAttribute("areaHead")
    public List<EmployeeData> areaHead(){
        return getHeadAreaEmployeesUseCase.getHeadAreaEmployees();
    }
}
