package com.personal.monkeyGram.sequrity;

import com.personal.monkeyGram.model.Role;
import com.personal.monkeyGram.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity create (User user){
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapRoles(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapRoles(List<Role> roles){
        return roles.stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
