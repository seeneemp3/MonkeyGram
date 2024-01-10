package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.LikeDao;
import com.personal.monkeyGram.dao.PostDao;
import com.personal.monkeyGram.exception.PostNotFoundException;
import com.personal.monkeyGram.model.Like;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.LikeService;
import com.personal.monkeyGram.service.PostService;
import com.personal.monkeyGram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final PostService postService;
    private final UserService userService;
    private final LikeDao likeDao;

    @Override
    public String addLike(String postId) {
        Post post = postService.findById(postId);
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<Like> like = likeDao.getByUserIdAndPostId(user.getId(), postId);
        if(like.isEmpty()){
           like = Optional.of(new Like(user.getId(), postId));
            post.setLikes(post.getLikes() + 1);
        }
        likeDao.save(like.get());
        postService.updatePost(post);
        return postId;
    }

    @Override
    public String removeLike(String postId) {
        Post post = postService.findById(postId);
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (likeDao.deleteByUserIdAndPostId(user.getId(), postId) > 0){
            post.setLikes(post.getLikes() - 1);
            postService.updatePost(post);
        }
        return postId;
    }
}
