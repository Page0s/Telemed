package com.telemed.telemed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.repository.AppUserRepository;

@Service
public class UserService {

    private AppUserRepository userRepository;

    public UserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getAllPatients(Long id) {
        return userRepository.findAllByUserTypeId(id);
    }

    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(AppUser appUser) {
        userRepository.save(appUser);
    }
}
