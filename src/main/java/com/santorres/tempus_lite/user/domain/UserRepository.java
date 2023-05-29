package com.santorres.tempus_lite.user.domain;

import java.util.Optional;

public interface UserRepository {
    boolean saveNewUser(User user);

    Optional<User> getUserByUserName(String username);
}
