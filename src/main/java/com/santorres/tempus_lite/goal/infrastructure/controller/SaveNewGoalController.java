package com.santorres.tempus_lite.goal.infrastructure.controller;

import com.santorres.tempus_lite.goal.use_case.SaveNewGoalUseCase;
import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.GetUserByUserNameUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class SaveNewGoalController {

    private final GetUserByUserNameUseCase userByUserNameUseCase;
    private final SaveNewGoalUseCase saveNewGoalUseCase;

    public SaveNewGoalController(GetUserByUserNameUseCase userByUserNameUseCase, SaveNewGoalUseCase saveNewGoalUseCase) {
        this.userByUserNameUseCase = userByUserNameUseCase;
        this.saveNewGoalUseCase = saveNewGoalUseCase;
    }

    @GetMapping("/goal/form/{fkArea}")
    public String form(@PathVariable String fkArea, Model model, Authentication authentication){

        String username = authentication.getName();

        User user = userByUserNameUseCase.getUserByUserName(username);

        model.addAttribute("fkArea",fkArea);
        model.addAttribute("createdBy",user.getId());

        return "/goal/create_goal_form";

    }

    @PostMapping("/goal/save")
    public String save(@RequestParam Map<String, String> body, RedirectAttributes flash){


        if (saveNewGoalUseCase.saveNewGoal(body)){

            flash.addFlashAttribute("success","El objetivo se guardó correctamente!");
        }else {

            flash.addFlashAttribute("error","La información no se guardó. Revise y vuelva a intentar.");
        }

        return "redirect:/goal/by/" + body.get("fkArea");


    }
}
