package com.edu.zucc.ygg.movie.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器添加跨域支持（如果是web.xml配置拦截器，请将@component删除）
 * response.setHeader("Access-Control-Allow-Credentials", "true");
 * @author zhizhao
 */

@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-headers", "realName");

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, PATCH, OPTIONS, HEAD, TRACE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Access-Token,timestamp,Authorization,If-Modified-Since");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}