package com.personal.monkeyGram.service;

import com.personal.monkeyGram.model.Post;

import java.util.Collection;
import java.util.List;

public interface PostService {
    String addPost(Post post);
    String deletePost(String postId);
    Post findById(String postId);
    List<Post> findPostsByUserId(String userId);
    Post updatePost(Post post);
    List<Post> top10Liked();
}
