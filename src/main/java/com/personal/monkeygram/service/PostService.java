package com.personal.monkeyGram.service;

import com.personal.monkeyGram.model.Post;

import java.util.List;

public interface PostService {
    String addPost(Post post);
    String deletePost(String postId);
    List<Post> findPostsByUserId(String userId);
}
