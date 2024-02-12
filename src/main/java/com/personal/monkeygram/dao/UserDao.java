package com.personal.monkeyGram.dao;

import com.personal.monkeyGram.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {
    User getUserById(String id);

    User getByUsername(String name);
}
