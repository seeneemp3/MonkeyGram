package com.personal.monkeyGram.dao;

import com.personal.monkeyGram.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeDao extends MongoRepository<Like, String> {
    Optional<Like> getByUserIdAndPostId(String userId, String postId);
    int deleteByUserIdAndPostId(String userId, String postId);
}
