package com.telemed.telemed.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.repository.UserRepository;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
