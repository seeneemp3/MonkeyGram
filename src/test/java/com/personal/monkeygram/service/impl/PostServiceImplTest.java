//package com.personal.monkeygram.service.impl;
//
//import com.personal.monkeyGram.dao.PostDao;
//import com.personal.monkeyGram.dao.UserDao;
//import com.personal.monkeyGram.model.Post;
//import com.personal.monkeyGram.service.impl.PostServiceImpl;
//import com.personal.monkeygram.config.TestConfig;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.testcontainers.shaded.com.trilead.ssh2.auth.AuthenticationManager;
//
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@Import(TestConfig.class)
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class PostServiceImplTest {
//    @InjectMocks
//    private PostServiceImpl postService;
//    @Mock
//    private PostDao postDao;
//    @Mock
//    private UserDao userDao;
//    @Mock
//    private AuthenticationManager auth;
//
//
//    @Test
//    public void findById(){
//        String id = "1";
//        Post post = new Post("", "");
//        Post testPost = postService.findById(id);
//        Mockito.when(postDao.findById(id)).thenReturn(Optional.of(post));
//        Mockito.verify(postDao).findById(id);
//        Assertions.assertEquals(post, testPost);
//    }
//
//}
