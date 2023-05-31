package com.santorres.tempus_lite.login.use_case;

import com.santorres.tempus_lite.login.domain.LoginRegistry;
import com.santorres.tempus_lite.login.domain.LoginRegistryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class SaveLoginRegistryUseCase {
    private final LoginRegistryRepository registryRepository;

    public SaveLoginRegistryUseCase(LoginRegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public void saveInitialLoginRegistry(String employeeId){

        LoginRegistry loginRegistry = new LoginRegistry(
                UUID.randomUUID().toString(),
                LocalDate.now(),
                LocalTime.now(),
                null,
                employeeId
        );

        registryRepository.saveInitialLoginRegistry(loginRegistry);

    }

}
