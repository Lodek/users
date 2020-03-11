package com.wipro.bartenders.users.api.common.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class AuditRequestFilter extends GenericFilterBean {

    @Autowired
    AuditBodyAction auditBodyAction;

    @Autowired
    List<String> auditIgnoredFields;

    @Autowired
    AuditRequestHeadersBuilder auditRequestHeadersBuilder;

    @Autowired
    AuditSupportValidator auditSupportValidator;

    @Autowired
    ResourceIdGenerator hexResourceIdGenerator;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try{
            AuditRequestWrapper req = new AuditRequestWrapper((HttpServletRequest) servletRequest);
            AuditRequestHeaders headers = auditRequestHeadersBuilder.build(null, null,
                hexResourceIdGenerator.getId(req), null, null);
            req.setAuditRequestHeaders(headers);

            //boolean auditEnabled = auditSupportValidator.isSupported(requestUri, method);

            filterChain.doFilter(servletRequest, servletResponse);

            HttpServletResponse filteredResponse = (HttpServletResponse) servletResponse;
            AuditRequestWrapper processedRequest = (AuditRequestWrapper) servletRequest;
            //auditBodyAction.auditBody(processedRequest.getAuditRequestHeaders(), processedRequest.getBody(), requestUri, method, filteredResponse.getStatus(), true, true);

        } catch (Exception e){
            e.printStackTrace();
            //auditBodyAction.auditBody();
        }

    }



}
