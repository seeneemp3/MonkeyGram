package com.personal.monkeyGram.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@AllArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean {
    private final JwtTokenProvider provider;
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        log.debug("---Incoming request");
        String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        log.debug("---With Token " + token);
        if (token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        if (token != null && provider.isValid(token)){
            Authentication auth = provider.getAuthentication(token);
            log.debug("---Token is valid ");
            if (auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.debug("---Authentication success ");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
