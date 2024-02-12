package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.CommentDao;
import com.personal.monkeyGram.exception.CommentNotFoundException;
import com.personal.monkeyGram.model.Comment;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.CommentService;
import com.personal.monkeyGram.service.PostService;
import com.personal.monkeyGram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final PostService postService;
    private final UserService userService;


    @Override
    public String addComment(String body, String postId) {
        Post post = postService.findById(postId);
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Comment comment = new Comment();
        comment.setBody(body);
        comment.setUserId(user.getId());
        comment.setPostId(postId);
        comment.setDate(LocalDateTime.now());

        String commentId = commentDao.save(comment).getId();
        post.getCommentIds().add(commentId);
        postService.updatePost(post);
        return commentId;
    }

    @Override
    public String deleteComment(String postId, String commentId) {
        Comment comment = commentDao.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found"));
        Post post = postService.findById(postId);
        post.getCommentIds().removeIf(commentInList -> commentInList.equals(commentId));
        postService.updatePost(post);
        commentDao.deleteById(comment.getId());
        return commentId;
    }

    @Override
    public List<Comment> findAllByPostId(String postId) {
        Post post = postService.findById(postId);
        return commentDao.findAllByPostId(postId);
    }

}
