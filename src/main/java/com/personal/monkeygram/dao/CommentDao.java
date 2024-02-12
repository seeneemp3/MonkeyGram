package com.personal.monkeyGram.dao;

import com.personal.monkeyGram.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends MongoRepository<Comment, String> {
    List<Comment> findAllByPostId(String postId);
}
