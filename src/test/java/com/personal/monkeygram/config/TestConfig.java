package com.personal.monkeygram.config;

import com.personal.monkeyGram.dao.PostDao;
import com.personal.monkeyGram.dao.UserDao;
import com.personal.monkeyGram.props.JwtProperties;
import com.personal.monkeyGram.security.JwtTokenProvider;
import com.personal.monkeyGram.security.JwtUserService;
import com.personal.monkeyGram.service.impl.AuthServiceImpl;
import com.personal.monkeyGram.service.impl.PostServiceImpl;
import com.personal.monkeyGram.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@TestConfiguration
@RequiredArgsConstructor
@Profile("test")
public class TestConfig {

    @Bean
    @Primary
    public BCryptPasswordEncoder testPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProperties jwtProperties(){
        var jwtProps = new JwtProperties();
        jwtProps.setSecret("cS4tMzRja3JxY1treDkvLy80MCxndnEuLi5reC0zLmY5MGp4aDg5dm0wOC0wNT05NCopXygpIys8Iz5WQz8rPiMpKDw=");
    return jwtProps;
    }

    @Bean
    public UserDetailsService userDetailsService(UserDao userDao){
         return new JwtUserService(userService(userDao));
    }

    @Bean
    public JwtTokenProvider tokenProvider(UserDao userDao){
        return new JwtTokenProvider(jwtProperties(), userDetailsService(userDao), userService(userDao));
    }

    @Bean
    @Primary
    public UserServiceImpl userService(UserDao userDao){
       return new UserServiceImpl(userDao, testPasswordEncoder());
    }

//    @Bean
//    @Primary
//    public PostServiceImpl postService(PostDao postDao, UserDao userDao){
//        return new PostServiceImpl(postDao, userService(userDao));
//    }

    @Bean
    @Primary
    public AuthServiceImpl authService(UserDao userDao, AuthenticationManager authenticationManager ){
        return new AuthServiceImpl(authenticationManager, userService(userDao), tokenProvider(userDao));
    }

    @Bean
    public UserDao userRepository() {
        return Mockito.mock(UserDao.class);
    }

    @Bean
    public PostDao taskRepository() {
        return Mockito.mock(PostDao.class);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return Mockito.mock(AuthenticationManager.class);
    }

}
