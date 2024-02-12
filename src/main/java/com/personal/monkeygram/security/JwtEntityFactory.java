package com.personal.monkeyGram.security;

import com.personal.monkeyGram.model.Role;
import com.personal.monkeyGram.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getUsername(),
                user.getPassword(),
                mapRoles(user.getRoles()),
                user.getId()
        );
    }

    private static List<GrantedAuthority> mapRoles(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
