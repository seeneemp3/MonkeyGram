package com.personal.monkeyGram.dao;

import com.personal.monkeyGram.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDao extends MongoRepository<Post, String> {
}
