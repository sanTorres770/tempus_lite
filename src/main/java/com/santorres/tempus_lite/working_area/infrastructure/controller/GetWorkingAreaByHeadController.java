package com.santorres.tempus_lite.working_area.infrastructure.controller;

import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.GetUserByUserNameUseCase;
import com.santorres.tempus_lite.working_area.domain.WorkingAreaData;
import com.santorres.tempus_lite.working_area.use_case.GetWorkingAreaByHeadUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetWorkingAreaByHeadController {

    private final GetWorkingAreaByHeadUseCase getWorkingAreaByHeadUseCase;
    private final GetUserByUserNameUseCase getUserByUserNameUseCase;

    public GetWorkingAreaByHeadController(GetWorkingAreaByHeadUseCase getWorkingAreaByHeadUseCase, GetUserByUserNameUseCase getUserByUserNameUseCase) {
        this.getWorkingAreaByHeadUseCase = getWorkingAreaByHeadUseCase;
        this.getUserByUserNameUseCase = getUserByUserNameUseCase;
    }

    @GetMapping("/area/by/head")
    public String get(Model model, Authentication authentication){

        String username = authentication.getName();

        User user = getUserByUserNameUseCase.getUserByUserName(username);

        WorkingAreaData workingAreaData = getWorkingAreaByHeadUseCase.getWorkingAreaByHead(user.getId());

        model.addAttribute("workingAreaData",workingAreaData);
        model.addAttribute("all",false);
        model.addAttribute("employeeName",user.getName());

        return "/area/area_list";

    }
}
