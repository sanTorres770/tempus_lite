package com.santorres.tempus_lite.main_page.infrastructure.controller;

import com.santorres.tempus_lite.login.domain.LoginRegistry;
import com.santorres.tempus_lite.login.use_case.GetLoginRegistryByEmployeeUseCase;
import com.santorres.tempus_lite.login.use_case.SaveLoginRegistryUseCase;
import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.GetUserByUserNameUseCase;
import com.santorres.tempus_lite.workshift.domain.Workshift;
import com.santorres.tempus_lite.workshift.use_case.GetWorkshiftByEmployeeUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Controller
public class MainPageController {

    private final GetUserByUserNameUseCase getUserByUserNameUseCase;
    private final SaveLoginRegistryUseCase saveLoginRegistryUseCase;
    private final GetLoginRegistryByEmployeeUseCase getLoginRegistryByEmployeeUseCase;
    private final GetWorkshiftByEmployeeUseCase getWorkshiftByEmployeeUseCase;

    public MainPageController(GetUserByUserNameUseCase getUserByUserNameUseCase, SaveLoginRegistryUseCase saveLoginEntranceRegistryUseCase, GetLoginRegistryByEmployeeUseCase getLoginRegistryByEmployeeUseCase, GetWorkshiftByEmployeeUseCase getWorkshiftByEmployeeUseCase) {
        this.getUserByUserNameUseCase = getUserByUserNameUseCase;
        this.saveLoginRegistryUseCase = saveLoginEntranceRegistryUseCase;
        this.getLoginRegistryByEmployeeUseCase = getLoginRegistryByEmployeeUseCase;
        this.getWorkshiftByEmployeeUseCase = getWorkshiftByEmployeeUseCase;
    }

    @GetMapping({"/", "/index", "/home"})
    public String index(Model model, Authentication authentication, RedirectAttributes flash){

        try {

            if (authentication.isAuthenticated()){


                String userName = authentication.getName();

                User user = getUserByUserNameUseCase.getUserByUserName(userName);

                LocalDate today = LocalDate.now();

                List<LoginRegistry> loginRegistryList = getLoginRegistryByEmployeeUseCase.getLoginRegistryByEmployee(user.getId(), today);

                if (loginRegistryList.size() == 0) {

                    saveLoginRegistryUseCase.saveInitialLoginRegistry(user.getId());

                    loginRegistryList = getLoginRegistryByEmployeeUseCase.getLoginRegistryByEmployee(user.getId(), today);

                    model.addAttribute("success", String.format("Bienvenido a Tempus %s. Usted ha iniciado su turno de trabajo hoy %s a las %s hora(s)."
                            ,user.getName(),LocalDate.now(), LocalTime.now()));
                    model.addAttribute("info", "Recuerde estar atento al finalizar su turno para que registre su hora de salida.");

                }

                Workshift workshift = getWorkshiftByEmployeeUseCase.getWorkshiftByEmployee(user.getId());
                LoginRegistry loginRegistry = loginRegistryList.get(0);

                LocalTime elapsedTime = LocalTime.now().minusHours(loginRegistry.getLoginHour().getHour());
                LocalTime remainingTime = workshift.getFinalHour().minusHours(LocalTime.now().getHour());

                model.addAttribute("loginRegistry", loginRegistry);
                model.addAttribute("workshift", workshift);
                model.addAttribute("today", today);
                model.addAttribute("elapsedTime", elapsedTime.getHour() + ":" + elapsedTime.getMinute() + ":" + elapsedTime.getSecond());
                model.addAttribute("remainingTime", remainingTime.getHour() + ":" + remainingTime.getMinute() + ":" + remainingTime.getSecond());


            }else {
                return "/login";
            }

        }catch (Exception e){

            System.out.println(e.getMessage());

            return "/login";
        }

        return "index";

    }
}
