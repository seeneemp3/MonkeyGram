package com.personal.monkeyGram.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoInit {
    @Bean
    CommandLineRunner initUsers(MongoTemplate mongoTemplate) {
        return args -> {
//            mongoTemplate.dropCollection(User.class);
//            mongoTemplate.dropCollection(Post.class);
//            mongoTemplate.dropCollection(Like.class);
//            mongoTemplate.dropCollection(Follow.class);
//            mongoTemplate.dropCollection(Comment.class);
//
//            User user1 = new User( "user1", "user1", "$2a$12$LzFxOTqJTpHse/Hi74VPBugj8csX549SGh.DO59tyAm25IWZFeIOC");
//            User user2 = new User( "user2", "user2", "$2a$12$LzFxOTqJTpHse/Hi74VPBugj8csX549SGh.DO59tyAm25IWZFeIOC");
//            user1.setRoles(List.of(Role.ROLE_USER, Role.ROLE_ADMIN));
//            user2.setRoles(List.of(Role.ROLE_USER));
//
//            mongoTemplate.save(user1);
//            mongoTemplate.save(user2);
//
//            Post post1 = new Post(user2.getId(), "hello", "url");
//            Post post2 = new Post(user2.getId(), "hello2", "url2");
//
//            mongoTemplate.save(post1);
//            mongoTemplate.save(post2);
        };
    }
}
