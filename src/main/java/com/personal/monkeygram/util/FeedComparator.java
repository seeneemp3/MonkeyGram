package com.personal.monkeyGram.util;

import com.personal.monkeyGram.model.Post;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Setter
public class FeedComparator implements Comparator<Post> {
    private List<String> followed;

    @Override
    public int compare(Post o1, Post o2) {
        if (followed.contains(o1.getUserId())) {
            return 1;
        } else if (followed.contains(o2.getUserId())) {
            return -1;
        } else {
            return 0;
        }
    }
}
