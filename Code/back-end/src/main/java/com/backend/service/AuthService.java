package com.backend.service;

import com.backend.jwt.JWTUtility;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public String loginWithEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        assert user != null;
        if (PasswordEncoder.getInstance().matches(password, user.getPassword())) {
            return JWTUtility.getInstance().generateTokenWithEmail(email);
        }
        return null;
    }
}