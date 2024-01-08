package com.personal.monkeyGram.service;

import com.personal.monkeyGram.model.Post;

import java.util.List;

public interface FeedService {
    List<Post> getTop10Rated();
}
