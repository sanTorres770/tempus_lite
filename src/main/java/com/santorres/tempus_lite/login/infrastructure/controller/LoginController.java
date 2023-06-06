package com.santorres.tempus_lite.login.infrastructure.controller;

import com.santorres.tempus_lite.login.use_case.SaveFinalHourRegistryUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    private final SaveFinalHourRegistryUseCase saveFinalHourRegistryUseCase;

    public LoginController(SaveFinalHourRegistryUseCase saveFinalHourRegistryUseCase) {
        this.saveFinalHourRegistryUseCase = saveFinalHourRegistryUseCase;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model,
                        Principal principal,
                        RedirectAttributes flash) {

        if (principal != null) {
            flash.addFlashAttribute("info", "Ya ha iniciado sesion anteriormente");
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error",
                               "Nombre de usuario y/o contrasena es incorrecta, por favor vuelva a intentarlo");
        }

        if (logout != null) {
            model.addAttribute("success",
                               "Ha cerrado sesión con éxito!");
        }

        return "login";
    }

    @GetMapping("/finish/{loginRegistryId}")
    public String finish(@PathVariable String loginRegistryId, RedirectAttributes flash){

        if (saveFinalHourRegistryUseCase.saveFinalHourRegistry(loginRegistryId)){
            flash.addFlashAttribute("success","Se guardó su hora de fin de turno correctamente!");
            flash.addFlashAttribute("info","Desde ahora puede cerrar su sesión.");
        }else {
            flash.addFlashAttribute("error","No se guardó su hora de fin de turno correctamente!");
        }

        return "redirect:/";
    }

}
