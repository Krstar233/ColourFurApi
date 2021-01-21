package com.colourfur.securityfilter.filter;

import com.colourfur.securityfilter.utils.TokenUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin/*")
@Order(10)
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, Object> token = TokenUtil.getTokenBody((HttpServletRequest) servletRequest);
        if (token.get("clf_group_id").equals("0")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ((HttpServletResponse) servletResponse).setStatus(500);
        return;
    }
}
