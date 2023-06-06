package com.santorres.tempus_lite.stats.infrastructure.controller;

import com.santorres.tempus_lite.login.domain.LoginRegistryData;
import com.santorres.tempus_lite.login.use_case.GetLoginRegistryListUseCase;
import com.santorres.tempus_lite.task.domain.TaskData;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import com.santorres.tempus_lite.task.use_case.GetTaskListByDateUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StatsByDateController {

    private final GetTaskListByDateUseCase getTaskListByDateUseCase;
    private final TaskRepository taskRepository;
    private final GetLoginRegistryListUseCase getLoginRegistryListUseCase;

    public StatsByDateController(GetTaskListByDateUseCase getTaskListByDateUseCase, TaskRepository taskRepository, GetLoginRegistryListUseCase getLoginRegistryListUseCase) {
        this.getTaskListByDateUseCase = getTaskListByDateUseCase;
        this.taskRepository = taskRepository;
        this.getLoginRegistryListUseCase = getLoginRegistryListUseCase;
    }

    @PutMapping("/stats")
    public String get(Model model, RedirectAttributes flash,
                      @RequestParam String initialDate, @RequestParam String finalDate){

        LocalDate initialDateFinal = LocalDate.parse(initialDate);
        LocalDate finalDateFinal = LocalDate.parse(finalDate);

        List<TaskData> taskDataList = getTaskListByDateUseCase.getTaskListByDate(initialDateFinal, finalDateFinal);

        int notFinishedTasksNumber = taskRepository.getNumberOfNotFinishedTasks();

        List<LoginRegistryData> loginRegistryDataList = getLoginRegistryListUseCase.getLoginRegistryList(initialDateFinal,finalDateFinal);

        Map<String, Double> workedTimeMap = new HashMap<>();

        for (LoginRegistryData loginRegistry: loginRegistryDataList) {

            String employeeName = loginRegistry.getEmployeeName();

            /*if (!workedTimeMap.containsKey(employeeName)){

                workedTimeMap.put(employeeName,)
            }*/

        }

        model.addAttribute("initialDate",initialDate);
        model.addAttribute("finalDate",finalDate);
        model.addAttribute("today",LocalDate.now());
        model.addAttribute("taskDataList",taskDataList);
        model.addAttribute("notFinishedTasksNumber",notFinishedTasksNumber);

        return "/stats/stats_accordion";
    }
}
