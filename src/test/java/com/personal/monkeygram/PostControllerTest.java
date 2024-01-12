package com.personal.monkeygram;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.monkeyGram.MonkeyGramApplication;
import com.personal.monkeyGram.controller.PostController;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.model.Role;
import com.personal.monkeyGram.security.JwtTokenProvider;
import com.personal.monkeyGram.service.CommentService;
import com.personal.monkeyGram.service.LikeService;
import com.personal.monkeyGram.service.PostService;
import com.personal.monkeygram.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PostController.class)
@ContextConfiguration(classes = {MonkeyGramApplication.class, TestConfig.class})
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private LikeService likeService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private String createTestToken(String username) {
        List<Role> roles = List.of(Role.ROLE_USER);
        return jwtTokenProvider.createAccessToken("123", "user000", roles);
    }
    private MockHttpServletRequestBuilder withJwt(MockHttpServletRequestBuilder requestBuilder, String token) {
        return requestBuilder.header("Authorization", "Bearer " + token);
    }


    @Test
    @WithMockUser(username = "user1", password = "123")
    public void testAddPost() throws Exception {
        Post post = new Post("123", "");
        post.setId("postId");
        String token = createTestToken("123");

        when(postService.addPost(any(Post.class))).thenReturn(post.getId());

        mockMvc.perform(withJwt(post("/api/v1/post"), token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("123")));
    }

    @Test
    @WithMockUser
    public void testDeletePost() throws Exception {
        String postId = "123";
        when(postService.deletePost("postId")).thenReturn("Post deleted successfully");

        mockMvc.perform(delete("/api/v1/post/" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(is("Post deleted successfully")));
    }

    @Test
    @WithMockUser
    public void testGetByUserId() throws Exception {
        String userId = "user1";
        when(postService.findPostsByUserId(userId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/post/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(Collections.emptyList())));
    }
}

