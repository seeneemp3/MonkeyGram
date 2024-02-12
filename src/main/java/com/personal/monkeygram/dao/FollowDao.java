package com.personal.monkeyGram.dao;

import com.personal.monkeyGram.model.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface FollowDao extends MongoRepository<Follow, String> {
    Follow findByUserId(String userId);

    Set<String> getFollowersByUserId(String userId);
}
