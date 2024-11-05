package com.example.Instargram.user.repository;

import com.example.Instargram.user.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findByEmail(String email);
}
