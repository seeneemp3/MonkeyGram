package com.personal.monkeygram.dao.impl;

import com.personal.monkeygram.dao.FollowDao;
import com.personal.monkeygram.dao.PostDao;
import com.personal.monkeygram.dao.UserDao;
import com.personal.monkeygram.model.Follow;
import com.personal.monkeygram.model.Post;
import com.personal.monkeygram.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;


@Component
public class FollowDoImpl implements FollowDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PostDao postDao;

    public FollowDoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, PostDao postDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @Override
    public List<Post> getFollowFeed(String userId, int max) {
        String sql = "select * from monkey_follow where follower_id = ?";
        List<Follow> follows = jdbcTemplate.query(sql,(rs, rowNum) -> makeFollow(rs),userId);

        Set<User> authors = follows.stream()
                .map(Follow::getAuthor)
                .map(userDao::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        if(authors.isEmpty()) {
            return Collections.emptyList();
        }

        return authors.stream()
                .map(postDao::findPostsByUser)
                .flatMap(java.util.Collection::stream)
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .limit(max)
                .collect(Collectors.toList());
    }

    private Follow makeFollow(ResultSet rs) throws SQLException{
        return new Follow(rs.getString("author_id"), rs.getString("follower_id"));
    }
}
