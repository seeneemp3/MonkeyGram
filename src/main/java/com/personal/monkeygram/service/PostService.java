package com.personal.monkeygram.service;

import org.springframework.stereotype.Service;
import com.personal.monkeygram.dao.PostDao;
import com.personal.monkeygram.exception.UserNotFoundException;
import com.personal.monkeygram.model.Post;
import com.personal.monkeygram.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserService userService;

    public PostService(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    public Collection<Post> findPostsByUser(String userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() ->new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден."));

        return postDao.findPostsByUser(user);
    }

    public Collection<Post> findPostsByUser(String authorId, Integer size, String sort) {
        return findPostsByUser(authorId)
                .stream()
                .sorted((p0, p1) -> {
                    int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
                    if (sort.equals("desc")) {
                        comp = -1 * comp; //обратный порядок сортировки
                    }
                    return comp;
                })
                .limit(size)
                .collect(Collectors.toList());
    }
}
