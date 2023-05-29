package com.santorres.tempus_lite.user.use_case;

import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.domain.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SaveNewUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SaveNewUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveNewUser(User user){

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.saveNewUser(user);
    }
}
