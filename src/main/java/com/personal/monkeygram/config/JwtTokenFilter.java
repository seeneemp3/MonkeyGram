package com.personal.monkeyGram.config;

import jakarta.servlet.*;

import java.io.IOException;

public class JwtTokenFilter implements Filter {
    public JwtTokenFilter(Object p0) {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
