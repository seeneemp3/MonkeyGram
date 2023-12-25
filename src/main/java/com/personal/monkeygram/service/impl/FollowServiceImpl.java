package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.FollowDao;
import com.personal.monkeyGram.model.Follow;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.FollowService;
import com.personal.monkeyGram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final UserService userService;
    private final FollowDao followDao;
    private final Authentication auth;

    @Override
    @Transactional
    public String follow(String userId) {
        User user = userService.getUserById(userId);
        User currentUser = userService.getUserByUsername(auth.getName());

        Follow userFollow = createOrGetFollowForUser(user.getId());
        Follow currentUserFollow = createOrGetFollowForUser(currentUser.getId());

        userFollow.getFollowers().add(currentUser.getId());
        currentUserFollow.getFollowed().add(user.getId());

        followDao.save(userFollow);
        followDao.save(currentUserFollow);

        return user.getId();
    }

    @Override
    @Transactional
    public String unfollow(String userId) {
        User user = userService.getUserById(userId);
        User currentUser = userService.getUserByUsername(auth.getName());

        Follow userFollow = createOrGetFollowForUser(user.getId());
        Follow currentUserFollow = createOrGetFollowForUser(currentUser.getId());

        userFollow.getFollowers().remove(currentUser.getId());
        currentUserFollow.getFollowed().remove(user.getId());

        followDao.delete(userFollow);
        followDao.delete(currentUserFollow);

        return user.getId();
    }

    @Override
    public Set<String> getFollowers(String userId) {
        Follow userFollow = createOrGetFollowForUser(userId);
        return userFollow.getFollowers();
    }

    @Override
    public Set<String> getFollowed(String userId) {
        Follow userFollow = createOrGetFollowForUser(userId);
        return userFollow.getFollowed();
    }

    private Follow createOrGetFollowForUser(String userId) {
        Follow follow = followDao.findByUserId(userId);
        return follow != null ? follow : new Follow(userId, new HashSet<>(), new HashSet<>());
    }
}
