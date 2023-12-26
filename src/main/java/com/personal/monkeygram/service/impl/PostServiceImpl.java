package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.PostDao;
import com.personal.monkeyGram.exception.PostNotFoundException;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    @Override
    public String addPost(Post post) {
        return postDao.save(post).getId();
    }

    @Override
    public String deletePost(String postId) {
        Post post = postDao.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        if(post != null){
            postDao.deleteById(postId);
        }
        return postId;
    }

    @Override
    public Post findById(String postId) {
        return postDao.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @Override
    public List<Post> findPostsByUserId(String userId) {
        return postDao.findPostsByUserId(userId);
    }

    @Override
    public Post updatePost(Post post) {
        Post original = findById(post.getId());

        if(!post.getCommentIds().equals(original.getCommentIds())){
            original.setCommentIds(post.getCommentIds());
        }
        if(!post.getDescription().equals(original.getDescription())){
            original.setDescription(post.getDescription());
        }
        if(!post.getLikes().equals(original.getLikes())){
            original.setLikes(post.getLikes());
        }
        if(!post.getUrl().equals(original.getUrl())){
            original.setUrl(post.getUrl());
        }
        return postDao.save(original);
    }
}
