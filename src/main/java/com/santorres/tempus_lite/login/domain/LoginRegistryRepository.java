package com.santorres.tempus_lite.login.domain;

import java.time.LocalDate;
import java.util.List;

public interface LoginRegistryRepository {
    void saveInitialLoginRegistry(LoginRegistry loginRegistry);

    List<LoginRegistryData> getLoginRegistryByEmployee(String employeeId, LocalDate today);

    boolean saveFinalHourRegistry(String loginRegistryId);

    List<LoginRegistryData> getLoginRegistryList(LocalDate date, LocalDate date2);
}
