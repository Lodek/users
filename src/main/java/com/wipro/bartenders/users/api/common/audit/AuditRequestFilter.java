package com.wipro.bartenders.users.api.common.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuditRequestFilter extends GenericFilterBean {

    AuditBodyAction auditBodyAction;

    List<String> auditIgnoredFields;

    AuditRequestHeadersBuilder auditRequestHeadersBuilder;

    @Autowired
    AuditSupportValidator auditSupportValidator;

    ResourceIdGenerator hexResourceIdGenerator;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        AuditBodyUtil util;

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String method = req.getMethod();
        String requestUri = req.getRequestURI();

        boolean auditEnabled = auditSupportValidator.isSupported(requestUri, method);

        String body = AuditBodyUtil.readBody(req);

        Map<String, String> headersMap = new HashMap();
        Enumeration<String> headerNames = req.getHeaderNames();

        while (headerNames.hasMoreElements()){
            headersMap.put(headerNames.nextElement(), null);
        }

        headersMap.forEach((key, value) -> headersMap.put(key, req.getHeader(key)));

        //auditRequestHeadersBuilder.build()

        AuditRequestWrapper request = new AuditRequestWrapper((HttpServletRequest) servletRequest);
        request.setBody(body);
        request.setAuditEnable(auditEnabled);
        request.setHeaderMap(headersMap);





        filterChain.doFilter(servletRequest, servletResponse);

        //auditBodyAction.auditBody();

    }



}
