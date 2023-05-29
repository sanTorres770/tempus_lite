package com.santorres.tempus_lite.user.use_case;

import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserByUserNameUseCase {
    private final UserRepository userRepository;

    public GetUserByUserNameUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserName(String username){
        return userRepository.getUserByUserName(username);
    }
}
