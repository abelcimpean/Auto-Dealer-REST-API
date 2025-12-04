package com.springframework.boot.AutoWebAPI.repository;

import com.springframework.boot.AutoWebAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);


}
