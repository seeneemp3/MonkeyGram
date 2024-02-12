//package com.personal.monkeygram.service.impl;
//
//import com.personal.monkeyGram.dao.PostDao;
//import com.personal.monkeyGram.exception.PostNotFoundException;
//import com.personal.monkeyGram.model.Post;
//import com.personal.monkeyGram.model.User;
//import com.personal.monkeyGram.service.UserService;
//import com.personal.monkeyGram.service.impl.PostServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.quality.Strictness;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//public class PostServiceTest {
//
//    @Autowired
//    private PostServiceImpl postService;
//
//    @MockBean
//    private PostDao postDao;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private Authentication authentication;
//
//    @BeforeEach
//    public void setUp() {
//        when(authentication.getName()).thenReturn("testUser");
//
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//    }
//
//
//    @Test
//    public void findById() {
//        String postId = "testId";
//        Post mockPost = new Post("", "");
//        when(postDao.findById(postId)).thenReturn(Optional.of(mockPost));
//
//        Post result = postService.findById(postId);
//
//        assertNotNull(result);
//        assertEquals(mockPost, result);
//    }
//
//    @Test
//    public void findByIdNotFound() {
//        String postId = "nonExistingId";
//        when(postDao.findById(postId)).thenReturn(Optional.empty());
//
//        assertThrows(PostNotFoundException.class, () -> postService.findById(postId));
//    }
//
//    @Test
//    public void addPost(){
//        String postId = "postId";
//        String userId = "userId";
//        Post mockPost = new Post("", "");
//        User mockUser = new User("testUser", "", "1");
//        mockUser.setId(userId);
//
//        Mockito.when(userService.getUserByUsername(anyString())).thenReturn(mockUser);
//        Mockito.doAnswer(inv -> {
//            Post savedPost = inv.getArgument(0);
//            savedPost.setId(postId);
//            return savedPost;
//        })
//                .when(postDao).save(mockPost);
//        String testPost = postService.addPost(mockPost);
//        Mockito.verify(postDao).save(mockPost);
//        Assertions.assertNotNull(testPost);
//        Assertions.assertEquals(testPost, postId);
//    }
//
//    @Test
//    public void deletePost(){
//        String postId = "postId";
//        String userId = "userId";
//        Post mockPost = new Post("", "");
//        mockPost.setId(postId);
//        mockPost.setUserId(userId);
//        User mockUser = new User("testUser", "", "1");
//        mockUser.setId(userId);
//        Mockito.when(userService.getUserByUsername(anyString())).thenReturn(mockUser);
//        Mockito.when(postDao.findById(postId)).thenReturn(Optional.of(mockPost));
//
//        postService.deletePost(postId);
//
//        Mockito.verify(postDao).deleteById(postId);
//    }
//
//    @Test
//    public void deletePost_WhenUserIsNotOwner() {
//        String postId = "postId";
//        User mockUser = new User("testUser", "","1");
//        mockUser.setId("1");
//        User anotherUser = new User("anotherUsername", "", "2");
//        anotherUser.setId("2");
//        Post post = new Post("", "Some content");
//        post.setUserId("2");
//
//        when(userService.getUserByUsername("testUser")).thenReturn(mockUser);
//        when(postDao.findById(postId)).thenReturn(Optional.of(post));
//
//        assertThrows(PostNotFoundException.class, () -> postService.deletePost(postId));
//
//       Mockito.verify(postDao, never()).deleteById(anyString());
//    }
//
//    @Test
//    public void findPostsByUserId(){
//        String userId = "userId";
//        Post post1 = new Post("", "Some content");
//        Post post2 = new Post("", "Some content");
//        Post post3 = new Post("", "Some content");
//        List<Post> expectedPosts = new ArrayList<>(List.of(post1, post2, post3));
//
//        when(postDao.findPostsByUserId(userId)).thenReturn(expectedPosts);
//
//        List<Post> actualPosts = postService.findPostsByUserId(userId);
//
//        assertEquals(expectedPosts, actualPosts);
//        Mockito.verify(postDao).findPostsByUserId(userId);
//    }
//
//    @Test
//    public void updatePostTest() {
//        Post originalPost = new Post("Original Description", "originalUrl.com" );
//        originalPost.setId("postId");
//        originalPost.setCommentIds(Arrays.asList("comment1", "comment2"));
//        originalPost.setLikes(10L);
//
//        Post updatedPost = new Post("Updated Description", "updatedUrl.com");
//        updatedPost.setId("postId");
//        updatedPost.setCommentIds(Arrays.asList("comment3", "comment4"));
//        updatedPost.setLikes(15L);
//
//        when(postDao.findById("postId")).thenReturn(Optional.of(originalPost));
//        when(postDao.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Post result = postService.updatePost(updatedPost);
//
//        assertEquals("Updated Description", result.getDescription());
//        assertEquals(Arrays.asList("comment3", "comment4"), result.getCommentIds());
//        assertEquals(15, result.getLikes());
//        assertEquals("updatedUrl.com", result.getUrl());
//
//        Mockito.verify(postDao).findById("postId");
//        Mockito.verify(postDao).save(any(Post.class));
//    }
//
//    @Test
//    public void top10Liked(){
//        String userId = "userId";
//        Post post1 = new Post("", "Some content");
//        Post post2 = new Post("", "Some content");
//        Post post3 = new Post("", "Some content");
//        List<Post> expectedPosts = new ArrayList<>(List.of(post1, post2, post3));
//        User mockUser = new User("testUser", "","1");
//        mockUser.setId(userId);
//        when(userService.getUserByUsername("testUser")).thenReturn(mockUser);
//
//        when(postDao.findTop10ByUserIdNotOrderByLikesDesc(userId)).thenReturn(expectedPosts);
//
//        List<Post> actualPosts = postService.top10Liked();
//
//        assertEquals(expectedPosts, actualPosts);
//        Mockito.verify(postDao).findTop10ByUserIdNotOrderByLikesDesc(userId);
//    }
//}
