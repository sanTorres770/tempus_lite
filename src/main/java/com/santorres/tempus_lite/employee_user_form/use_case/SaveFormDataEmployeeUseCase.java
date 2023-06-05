package com.santorres.tempus_lite.employee_user_form.use_case;

import com.santorres.tempus_lite.employee.domain.Employee;
import com.santorres.tempus_lite.employee.use_case.SaveNewEmployeeUseCase;
import com.santorres.tempus_lite.employee_role.use_case.SaveNewUserAssignedRoleUseCase;
import com.santorres.tempus_lite.task.domain.TaskRepository;
import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.SaveNewUserUseCase;
import com.santorres.tempus_lite.workshift.domain.WorkshiftRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SaveFormDataEmployeeUseCase {
    private final SaveNewUserUseCase saveNewUserUseCase;
    private final SaveNewEmployeeUseCase saveNewEmployeeUseCase;
    private final SaveNewUserAssignedRoleUseCase assignedRoleUseCase;
    private final WorkshiftRepository workshiftRepository;
    private final TaskRepository taskRepository;


    public SaveFormDataEmployeeUseCase(SaveNewUserUseCase saveNewUserUseCase, SaveNewEmployeeUseCase saveNewEmployeeUseCase, SaveNewUserAssignedRoleUseCase assignedRoleUseCase, WorkshiftRepository workshiftRepository, TaskRepository taskRepository) {
        this.saveNewUserUseCase = saveNewUserUseCase;
        this.saveNewEmployeeUseCase = saveNewEmployeeUseCase;
        this.assignedRoleUseCase = assignedRoleUseCase;
        this.workshiftRepository = workshiftRepository;
        this.taskRepository = taskRepository;
    }


    public boolean saveFormData(Map<String,String> data){

        User user = createUser(data);
        Employee employee = createEmployee(data);

        if (saveNewEmployeeUseCase.saveNewEmployee(employee) && saveNewUserUseCase.saveNewUser(user)){

            assignedRoleUseCase.saveAssignedRole(data.get("idDocument"), Integer.parseInt(data.get("fkRole")));

            workshiftRepository.assignEmployeeWorkshift(data.get("idDocument"), Integer.parseInt(data.get("workshiftId")));

            taskRepository.assignTaskNullToEmployee(data.get("idDocument"), null, null);

            return true;
        }

        return false;

    }

    private Employee createEmployee(Map<String, String> data) {

        return new Employee(
                data.get("idDocument"),
                data.get("name"),
                data.get("lastName"),
                data.get("telephone"),
                data.get("email"),
                Integer.parseInt(data.get("fkRole")),
                null
                );
    }

    private User createUser(Map<String, String> data) {

        String userName = data.get("email").split("@")[0];

        return new User(
                data.get("idDocument"),
                data.get("name"),
                data.get("lastName"),
                userName,
                data.get("password"),
                true
        );
    }


}
