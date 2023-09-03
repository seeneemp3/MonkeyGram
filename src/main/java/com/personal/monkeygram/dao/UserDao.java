package com.personal.monkeygram.dao;

import com.personal.monkeygram.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserById(String id);
}