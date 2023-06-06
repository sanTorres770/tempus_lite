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

            // si esta autenticado se busca el registro de login de la fecha actual

            if (authentication.isAuthenticated()){


                String userName = authentication.getName();

                User user = getUserByUserNameUseCase.getUserByUserName(userName);

                LocalDate today = LocalDate.now();

                List<LoginRegistry> loginRegistryList = getLoginRegistryByEmployeeUseCase.getLoginRegistryByEmployee(user.getId(), today);


                // si no tiene login en la fecha actual, se crea el registro y se obtiene para luego usarlo en el calculo de horas de trabajo

                if (loginRegistryList.size() == 0) {

                    saveLoginRegistryUseCase.saveInitialLoginRegistry(user.getId());

                    loginRegistryList = getLoginRegistryByEmployeeUseCase.getLoginRegistryByEmployee(user.getId(), today);

                    model.addAttribute("success", String.format("Bienvenido a Tempus %s. Usted ha iniciado su turno de trabajo hoy %s a las %s hora(s)."
                            ,user.getName(),LocalDate.now(), LocalTime.now()));
                    model.addAttribute("info", "Recuerde estar atento al finalizar su turno para que registre su hora de salida.");

                }

                //se obtiene el horario que fue asignado para calcular las horas de trabajo

                Workshift workshift = getWorkshiftByEmployeeUseCase.getWorkshiftByEmployee(user.getId());
                LoginRegistry loginRegistry = loginRegistryList.get(0);


                // se calculan los minutos de diferencia entre entrada y salida, se convierten luego a horas y minutos separados.
                // calculando si la hora de entrada es mayor a la de salida, es decir, si el turno empieza desde el dia anterior


                double initialMinutes = ((workshift.getInitialHour().getHour() * 60) + workshift.getInitialHour().getMinute());
                double finalMinutes = ((workshift.getFinalHour().getHour() * 60) + (workshift.getFinalHour().getMinute()));

                double workHoursInMinutes = 0;

                if (workshift.getInitialHour().isAfter(workshift.getFinalHour())){

                    workHoursInMinutes = (1440 - initialMinutes) + finalMinutes -60;

                }else {

                    workHoursInMinutes = finalMinutes - initialMinutes - 60;
                }

                String workHoursFormatted = workHoursInMinutes / 60 + "00";
                String[] minutesAndHours = workHoursFormatted.split("\\.");

                // se calcula el tiempo en minutos de entrada y minutos actuales para saber si ya se completo el turno
                // y para mostrar tiempo restante y transcurrido desde el login.

                double currentMinutes = ((LocalTime.now().getHour()*60) + LocalTime.now().getMinute());
                double loginMinutes = ((loginRegistry.getLoginHour().getHour()*60)+loginRegistry.getLoginHour().getMinute());
                double elapsedTurnTime = currentMinutes - initialMinutes;

                boolean completedTime = currentMinutes >= finalMinutes;

                double remainingTime = finalMinutes - elapsedTurnTime -60;

                double elapsedLoginTime = currentMinutes - loginMinutes;

                String remainingTimeFormatted = remainingTime /60 + "00";
                String[] remainingTimeInMinutesAndHours = remainingTimeFormatted.split("\\.");

                String elapsedLoginTimeFormatted = elapsedLoginTime /60 + "00";
                String[] elapsedLoginTimeInMinutesAndHours = elapsedLoginTimeFormatted.split("\\.");

                model.addAttribute("loginRegistry", loginRegistry);
                model.addAttribute("workshift", workshift);
                model.addAttribute("today", today);
                model.addAttribute("completedTime", completedTime);
                model.addAttribute("hours", minutesAndHours[0]);
                model.addAttribute("minutes", (Double.parseDouble(minutesAndHours[1].substring(0,2))/100)*60);
                model.addAttribute("remainingHours", remainingTimeInMinutesAndHours[0]);
                model.addAttribute("remainingMinutes", (Double.parseDouble(remainingTimeInMinutesAndHours[1].substring(0,2))/100)*60);
                model.addAttribute("elapsedHours", elapsedLoginTimeInMinutesAndHours[0]);
                model.addAttribute("elapsedMinutes", (Double.parseDouble(elapsedLoginTimeInMinutesAndHours[1].substring(0,2))/100)*60);

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
