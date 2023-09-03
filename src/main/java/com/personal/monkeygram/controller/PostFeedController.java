package com.personal.monkeygram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.personal.monkeygram.exception.IncorrectParameterException;
import com.personal.monkeygram.model.FeedParams;
import com.personal.monkeygram.model.Post;
import com.personal.monkeygram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/feed/friends")
public class PostFeedController {

    private final PostService postService;

    public PostFeedController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    List<Post> getFriendsFeed(@RequestBody FeedParams feedParams) {
        if (!feedParams.getSort().equalsIgnoreCase("asc") || !feedParams.getSort().equalsIgnoreCase("desc") ) {
            throw new IncorrectParameterException("sort");
        }
        if (feedParams.getSize() == null || feedParams.getSize() <= 0) {
            throw new IncorrectParameterException("size");
        }
        if (feedParams.getFriendsEmails().isEmpty()) {
            throw new IncorrectParameterException("friendsEmails");
        }

        List<Post> result = new ArrayList<>();
        for (String friendEmail : feedParams.getFriendsEmails()) {
            result.addAll(postService.findPostsByUser(friendEmail, feedParams.getSize(), feedParams.getSort()));
        }
        return result;
    }
}
