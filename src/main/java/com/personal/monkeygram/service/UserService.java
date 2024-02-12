package com.personal.monkeyGram.service;

import com.personal.monkeyGram.model.User;

import java.util.List;


public interface UserService {
    String addUser(User user);

    String deleteUser(String userId);

    User updateUser(User user);

    List<User> getAll();

    User getUserByUsername(String name);

    User getUserById(String id);
}
