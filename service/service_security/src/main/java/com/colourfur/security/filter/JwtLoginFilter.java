package com.colourfur.security.filter;


import com.colourfur.security.utils.TokenUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(filterName = "jwtLoginFilter", urlPatterns = "/login")
//@Order(1)
public class JwtLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Long uid = TokenUtil.getUidFromToken(req);
        if (uid != null)
            req.setAttribute("uid", uid);
    }
}
