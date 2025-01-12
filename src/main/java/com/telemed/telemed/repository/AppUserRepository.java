package com.telemed.telemed.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telemed.telemed.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public Optional<AppUser> findByEmail(String email);

    public List<AppUser> findAllByUserType(int userType);

    public Optional<AppUser> findById(Long id);
}
