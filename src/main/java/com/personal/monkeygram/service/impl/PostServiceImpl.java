package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.PostDao;
import com.personal.monkeyGram.exception.PostNotFoundException;
import com.personal.monkeyGram.exception.UserNotFoundException;
import com.personal.monkeyGram.model.Post;
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
    public List<Post> findPostsByUserId(String userId) {
        return postDao.findPostsByUserId(userId);
    }
}
