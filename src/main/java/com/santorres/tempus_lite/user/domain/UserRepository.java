package com.santorres.tempus_lite.user.domain;

import java.util.Optional;

public interface UserRepository {
    boolean saveNewUser(User user);

    User getUserByUserName(String username);
}
