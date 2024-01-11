package com.personal.monkeygram.service.impl;

import com.personal.monkeyGram.dao.PostDao;
import com.personal.monkeyGram.exception.PostNotFoundException;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.UserService;
import com.personal.monkeyGram.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
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

    @MockBean
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        when(authentication.getName()).thenReturn("testUser");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }


    @Test
    public void findById() {
        String postId = "testId";
        Post mockPost = new Post("", "");
        when(postDao.findById(postId)).thenReturn(Optional.of(mockPost));

        Post result = postService.findById(postId);

        assertNotNull(result);
        assertEquals(mockPost, result);
    }

    @Test
    public void findByIdNotFound() {
        String postId = "nonExistingId";
        when(postDao.findById(postId)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.findById(postId));
    }

    @Test
    public void addPost(){
        String postId = "postId";
        String userId = "userId";
        Post mockPost = new Post("", "");
        User mockUser = new User("testUser", "", "1");
        mockUser.setId(userId);

        Mockito.when(userService.getUserByUsername(anyString())).thenReturn(mockUser);
        Mockito.doAnswer(inv -> {
            Post savedPost = inv.getArgument(0);
            savedPost.setId(postId);
            return savedPost;
        })
                .when(postDao).save(mockPost);
        String testPost = postService.addPost(mockPost);
        Mockito.verify(postDao).save(mockPost);
        Assertions.assertNotNull(testPost);
        Assertions.assertEquals(testPost, postId);
    }

    @Test
    public void deletePost(){
        String postId = "postId";
        String userId = "userId";
        Post mockPost = new Post("", "");
        mockPost.setId(postId);
        mockPost.setUserId(userId);
        User mockUser = new User("testUser", "", "1");
        mockUser.setId(userId);
        Mockito.when(userService.getUserByUsername(anyString())).thenReturn(mockUser);
        Mockito.when(postDao.findById(postId)).thenReturn(Optional.of(mockPost));

        postService.deletePost(postId);

        Mockito.verify(postDao).deleteById(postId);
    }

    @Test
    public void deletePost_WhenUserIsNotOwner() {
        String postId = "postId";
        User mockUser = new User("testUser", "","1");
        mockUser.setId("1");
        User anotherUser = new User("anotherUsername", "", "2");
        anotherUser.setId("2");
        Post post = new Post("", "Some content");
        post.setUserId("2");

        when(userService.getUserByUsername("testUser")).thenReturn(mockUser);
        when(postDao.findById(postId)).thenReturn(Optional.of(post));

        assertThrows(PostNotFoundException.class, () -> postService.deletePost(postId));

       Mockito.verify(postDao, never()).deleteById(anyString());
    }
}
