//package com.personal.monkeygram.config;
//
//
//import com.personal.monkeyGram.dao.PostDao;
//import com.personal.monkeyGram.service.UserService;
//import com.personal.monkeyGram.service.impl.PostServiceImpl;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//@TestConfiguration
//@Profile("test")
//public class TestConfig2 {
//
//    @Bean
//    @Primary
//    public PostDao postDao() {
//        return Mockito.mock(PostDao.class);
//    }
//
//    @Bean
//    @Primary
//    public UserService userService() {
//        return Mockito.mock(UserService.class);
//    }
//
//    @Bean
//    @Primary
//    public PostServiceImpl postService() {
//        return new PostServiceImpl(postDao(), userService());
//    }
//}
