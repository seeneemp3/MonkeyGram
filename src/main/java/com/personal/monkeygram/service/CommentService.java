package com.personal.monkeyGram.service;

import com.personal.monkeyGram.model.Comment;


public interface CommentService {
   String addComment(Comment comment);
   String deleteComment(String commentId);
}
