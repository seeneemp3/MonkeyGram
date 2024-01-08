package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.FeedService;
import com.personal.monkeyGram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final PostService postService;

    public List<Post> getTop10Rated(){
        List<Post> list = postService.top10Liked();
        list.sort(Comparator.comparing(Post::getLikes)
                .thenComparing(post -> (long) post.getCommentIds().size()).reversed());
        return list;
    }
}
