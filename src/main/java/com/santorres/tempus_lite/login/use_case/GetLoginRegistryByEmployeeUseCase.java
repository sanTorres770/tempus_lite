package com.santorres.tempus_lite.login.use_case;

import com.santorres.tempus_lite.login.domain.LoginRegistry;
import com.santorres.tempus_lite.login.domain.LoginRegistryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetLoginRegistryByEmployeeUseCase {

    private final LoginRegistryRepository loginRegistryRepository;

    public GetLoginRegistryByEmployeeUseCase(LoginRegistryRepository loginRegistryRepository) {
        this.loginRegistryRepository = loginRegistryRepository;
    }

    public List<LoginRegistry> getLoginRegistryByEmployee(String employeeId, LocalDate today){
        return loginRegistryRepository.getLoginRegistryByEmployee(employeeId,today);
    }
}
