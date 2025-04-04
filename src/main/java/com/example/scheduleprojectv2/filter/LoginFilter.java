package com.example.scheduleprojectv2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/user/signup", "/login", "/logout","/id"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String uri = request.getRequestURI();

        if( !isWhiteList(uri)) {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("LOGIN_USER") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }


    private boolean isWhiteList(String uri) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, uri);
    }
}
