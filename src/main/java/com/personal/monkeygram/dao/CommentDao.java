package com.personal.monkeyGram.dao;

import com.personal.monkeyGram.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comment,String> {
}
