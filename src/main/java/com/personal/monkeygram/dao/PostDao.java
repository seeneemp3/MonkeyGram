package com.personal.monkeygram.dao;

import com.personal.monkeygram.model.Post;
import com.personal.monkeygram.model.User;

import java.util.Collection;

public interface PostDao {
    Collection<Post> findPostsByUser(User user);
}
