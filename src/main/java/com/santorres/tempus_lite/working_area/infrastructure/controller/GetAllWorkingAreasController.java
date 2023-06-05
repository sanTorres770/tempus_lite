package com.santorres.tempus_lite.working_area.infrastructure.controller;

import com.santorres.tempus_lite.working_area.domain.WorkingAreaData;
import com.santorres.tempus_lite.working_area.use_case.GetAllWorkingAreasUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetAllWorkingAreasController {

    private final GetAllWorkingAreasUseCase getAllWorkingAreasUseCase;

    public GetAllWorkingAreasController(GetAllWorkingAreasUseCase getAllWorkingAreasUseCase) {
        this.getAllWorkingAreasUseCase = getAllWorkingAreasUseCase;
    }


    @GetMapping("/area/all")
    public String getAll(Model model){

        List<WorkingAreaData> workingAreaDataList = getAllWorkingAreasUseCase.getAll();

        model.addAttribute("workingAreaDataList",workingAreaDataList);
        model.addAttribute("all",true);

        return "/area/area_list";
    }
}
