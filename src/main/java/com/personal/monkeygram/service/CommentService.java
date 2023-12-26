package com.personal.monkeyGram.service;

import com.personal.monkeyGram.model.Comment;

import java.util.List;


public interface CommentService {
   String addComment(String body, String postId);
   String deleteComment(String postId, String commentId);
   List<Comment> findAllByPostId(String postId);
}
