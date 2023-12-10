package com.personal.monkeygram.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.personal.monkeygram.dao.UserDao;
import com.personal.monkeygram.model.User;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Optional<User> findUserById(String id) {

        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from cat_user where id = ?", id);

        if(userRows.next()) {
            User user = new User(
                    userRows.getString("id"),
                    userRows.getString("username"),
                    userRows.getString("nickname"));

            log.info("Found user: {} {}", user.getId(), user.getNickname());

            return Optional.of(user);
        } else {
            log.info("User with id {} was not found.", id);
            return Optional.empty();
        }
    }
}