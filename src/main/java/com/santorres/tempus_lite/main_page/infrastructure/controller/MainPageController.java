package com.santorres.tempus_lite.main_page.infrastructure.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class MainPageController {

    @GetMapping({"/", "/index", "/home"})
    public String index(Model model, Authentication authentication) {
//        String userName = authentication.getName();
        //model.addAttribute("userName", userName);
        return "index";
    }
}
