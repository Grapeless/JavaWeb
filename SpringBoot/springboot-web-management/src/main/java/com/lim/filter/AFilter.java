package com.lim.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/depts/*")
public class AFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("A-放行前逻辑");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("A-放行后逻辑");

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
