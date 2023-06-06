package com.santorres.tempus_lite.login.use_case;

import com.santorres.tempus_lite.login.domain.LoginRegistryRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveFinalHourRegistryUseCase {
    private final LoginRegistryRepository loginRegistryRepository;

    public SaveFinalHourRegistryUseCase(LoginRegistryRepository loginRegistryRepository) {
        this.loginRegistryRepository = loginRegistryRepository;
    }

    public boolean saveFinalHourRegistry(String loginRegistryId){
        return loginRegistryRepository.saveFinalHourRegistry(loginRegistryId);
    }
}
