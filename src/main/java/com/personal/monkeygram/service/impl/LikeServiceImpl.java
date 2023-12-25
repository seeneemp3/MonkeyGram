package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.LikeDao;
import com.personal.monkeyGram.dao.PostDao;
import com.personal.monkeyGram.exception.PostNotFoundException;
import com.personal.monkeyGram.model.Like;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.LikeService;
import com.personal.monkeyGram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final PostDao postDao;
    private final UserService userService;
    private final LikeDao likeDao;
    private final Authentication auth;

    @Override
    public String addLike(String postId) {
        Post post = postDao.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        User user = userService.getUserByUsername(auth.getName());
        Optional<Like> like = likeDao.getByUserIdAndPostId(user.getId(), postId);
        if(like.isEmpty()){
           like = Optional.of(new Like(user.getId(), postId));
            post.setLikes(post.getLikes() + 1);
        }
        likeDao.save(like.get());
        postDao.save(post);
        return postId;
    }

    @Override
    public String removeLike(String postId) {
        Post post = postDao.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        User user = userService.getUserByUsername(auth.getName());
        if (likeDao.deleteByUserIdAndPostId(user.getId(), postId) > 0){
            post.setLikes(post.getLikes() - 1);
            postDao.save(post);
        }
        return postId;
    }
}
