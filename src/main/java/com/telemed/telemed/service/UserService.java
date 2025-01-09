package com.telemed.telemed.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getAllPatients(Long id) {
        return userRepository.findAllByUserTypeId(id);
    }
}
