package com.santorres.tempus_lite.login.domain;

import java.time.LocalDate;
import java.util.List;

public interface LoginRegistryRepository {
    void saveInitialLoginRegistry(LoginRegistry loginRegistry);

    List<LoginRegistry> getLoginRegistryByEmployee(String employeeId, LocalDate today);
}
