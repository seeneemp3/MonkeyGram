package com.personal.monkeygram.service;

import org.springframework.stereotype.Service;
import com.personal.monkeygram.dao.UserDao;
import com.personal.monkeygram.model.User;

import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findUserById(String id) {
        return userDao.findUserById(id);
    }
}