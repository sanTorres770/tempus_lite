package com.santorres.tempus_lite.login.use_case;

import com.santorres.tempus_lite.login.domain.LoginRegistryData;
import com.santorres.tempus_lite.login.domain.LoginRegistryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetLoginRegistryListUseCase {
    private final LoginRegistryRepository loginRegistryRepository;

    public GetLoginRegistryListUseCase(LoginRegistryRepository loginRegistryRepository) {
        this.loginRegistryRepository = loginRegistryRepository;
    }

    public List<LoginRegistryData> getLoginRegistryList(LocalDate date1, LocalDate date2){
        return loginRegistryRepository.getLoginRegistryList(date1,date2);
    }
}
