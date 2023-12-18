package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.exception.UserNotFoundException;
import com.personal.monkeyGram.dao.UserDao;
import com.personal.monkeyGram.model.Role;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(Role.ROLE_USER));
        return userDao.save(user).getId();
    }

    @Override
    public String deleteUser(String userId) {
        User user = userDao.findById(userId).orElseThrow(() -> new UserNotFoundException("unf"));
        if(user != null){
            userDao.deleteById(userId);
        }
        return userId;
    }

    @Override
    public User updateUser(User user) {
        User original = userDao.getUserById(user.getId());

        if(!user.getNickname().equals(original.getNickname())){
            original.setNickname(user.getNickname());
        }
        if(!user.getUsername().equals(original.getUsername())){
            original.setUsername(user.getUsername());
        }
        return userDao.save(original);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }


    @Override
    public User getUserByUsername(String username) {
        User user = userDao.getByUsername(username);
        if (user == null) {
           throw new UserNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User getUserById(String userId) {
        return userDao.findById(userId).orElseThrow(() -> new UserNotFoundException("unf"));
    }


}
