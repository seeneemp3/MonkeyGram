package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.CommentDao;
import com.personal.monkeyGram.model.Comment;
import com.personal.monkeyGram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    @Override
    public String addComment(Comment comment) {
        return commentDao.save(comment).getId();
    }

    @Override
    public String deleteComment(String commentId) {
        commentDao.deleteById(commentId);
        return commentId;
    }
}
