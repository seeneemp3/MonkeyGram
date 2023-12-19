package com.personal.monkeyGram.dao;

import com.personal.monkeyGram.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostDao extends MongoRepository<Post, String> {
    List<Post> findPostsByUserId(String userId);
}
