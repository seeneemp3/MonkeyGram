package com.personal.monkeyGram.service;

import java.util.Set;

public interface FollowService {
    String follow(String userId);

    String unfollow(String userId);

    Set<String> getFollowers(String userId);

    Set<String> getFollowed(String userId);
}
