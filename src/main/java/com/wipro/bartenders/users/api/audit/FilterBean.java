package com.wipro.bartenders.users.api.audit;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterBean extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) request;
        System.out.println(r.getContextPath());
        System.out.println(r.getMethod());
        filterChain.doFilter(request, response);
        HttpServletResponse rr = (HttpServletResponse) response;
        System.out.println(rr.getStatus());
        }
    }

