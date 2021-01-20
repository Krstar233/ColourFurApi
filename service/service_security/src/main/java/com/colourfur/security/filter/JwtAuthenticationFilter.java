package com.colourfur.security.filter;


import com.colourfur.security.utils.TokenUtil;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "jwtAuthenticationFilter", urlPatterns = "/*")
@Order(1)
public class JwtAuthenticationFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Set.of("/login/**", "/register/**", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String path = req.getServletPath();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String p : ALLOWED_PATHS) {
            if (pathMatcher.match(p, path)){ //路径匹配
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        HttpServletResponse resp  = (HttpServletResponse) servletResponse;
        Long uid = TokenUtil.getUidFromToken(req);
        if (uid == null){
            resp.setStatus(500);
            return;
        }
        req.setAttribute("uid", uid);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}