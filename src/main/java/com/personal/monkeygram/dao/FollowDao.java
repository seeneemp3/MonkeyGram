package com.personal.monkeygram.dao;

import com.personal.monkeygram.model.Post;

import java.util.List;

public interface FollowDao {
    List<Post> getFollowFeed(String userId, int max);
}
