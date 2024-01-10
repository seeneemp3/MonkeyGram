package com.personal.monkeyGram.service.impl;

import com.personal.monkeyGram.dao.PostDao;
import com.personal.monkeyGram.exception.PostNotFoundException;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import org.mockito.junit.jupiter.MockitoSettings;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PostServiceTest {

    @Autowired
    private PostServiceImpl postService;

    @MockBean
    private PostDao postDao;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void findByIdTest() {
        String postId = "testId";
        Post mockPost = new Post("", "");
        when(postDao.findById(postId)).thenReturn(Optional.of(mockPost));

        Post result = postService.findById(postId);

        assertNotNull(result);
        assertEquals(mockPost, result);
    }

    @Test
    public void findByIdNotFoundTest() {
        String postId = "nonExistingId";
        when(postDao.findById(postId)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.findById(postId));
    }
}
