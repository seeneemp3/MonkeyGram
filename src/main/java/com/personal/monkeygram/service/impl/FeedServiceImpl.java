package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.FeedService;
import com.personal.monkeyGram.service.FollowService;
import com.personal.monkeyGram.service.PostService;
import com.personal.monkeyGram.service.UserService;
import com.personal.monkeyGram.util.FeedComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;
    private final Authentication auth;

    public List<Post> getTop10Rated(){
        FeedComparator feedComparator = new FeedComparator();
        User user = userService.getUserByUsername(auth.getName());
        List<String> followed = new ArrayList<>(followService.getFollowed(user.getId()));
        feedComparator.setFollowed(followed);
        List<Post> list = postService.top10Liked();

        list.sort(feedComparator
                .thenComparing(Post::getLikes)
                .thenComparing(post -> (long) post.getCommentIds().size())
                .reversed());
        return list;
    }
}
