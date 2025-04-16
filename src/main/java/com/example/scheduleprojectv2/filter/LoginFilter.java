package com.example.scheduleprojectv2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/users/signup", "/authors/login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();


        log.info("로그인 필터 로직 실행");

        if(!isWhiteList(uri)) {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("userId") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isWhiteList(String uri) {

        return PatternMatchUtils.simpleMatch(WHITE_LIST, uri);
    }
}
