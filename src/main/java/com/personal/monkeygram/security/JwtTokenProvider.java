package com.personal.monkeyGram.security;

import com.personal.monkeyGram.exception.AccessDeniedException;
import com.personal.monkeyGram.model.Role;
import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.props.JwtProperties;
import com.personal.monkeyGram.security.auth.JwtResponse;
import com.personal.monkeyGram.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {
    private final JwtProperties properties;
    private final UserDetailsService detailsService;
    private final UserService userService;
    private Key key;
    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
    }

    public String createAccessToken(String userId, String username, List<Role> roles){
        log.debug(String.format("---Access token creation with userId: %s, username: %s and roles %s", userId, username, roles.toString()));
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);
        claims.put("roles", roles);

        Date now = new Date();
        Date exp = new Date(now.getTime() + properties.getAccess());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)
                .setIssuedAt(now)
                .signWith(key)
                .compact();
    }
    public String createRefreshToken(String userId, String username){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", userId);

        Date now = new Date();
        Date exp = new Date(now.getTime() + properties.getRefresh());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(exp)
                .setIssuedAt(now)
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshToken(String refreshToken) {
        if (!isValid(refreshToken)){
            throw new AccessDeniedException("Access denied");
        }
        String userid = getIdFromToken(refreshToken);
        User user = userService.getUserById(userid);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(userid);
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(
                createAccessToken(userid, user.getUsername(), user.getRoles())
        );
        jwtResponse.setRefreshToken(createRefreshToken(userid, user.getUsername()));
        return jwtResponse;

    }

    public boolean isValid(String token){
        Jws<Claims> claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private String getIdFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id").toString();
    }
    private String getUsername(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(final String token) {
        String username = getUsername(token);
        UserDetails userDetails = detailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities());
    }

}
