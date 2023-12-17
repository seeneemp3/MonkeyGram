package com.personal.monkeyGram.sequrity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final JwtTokenProvider provider;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (token != null && !token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        try {
            if (token != null && !token.isBlank() && !token.isEmpty() && provider.isValid(token)){
                Authentication auth = provider.getAuthentication(token);
                if (auth != null){
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }catch (Exception e){
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
