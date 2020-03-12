package com.wipro.bartenders.users.api.common.audit;

import org.apache.catalina.connector.ResponseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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

            filterChain.doFilter(req, servletResponse);

            String method = req.getMethod();
            String url = req.getRequestURI();
            ResponseFacade facade = (ResponseFacade) servletResponse;
            if (auditSupportValidator.isSupported(url, method)){
                auditBodyAction.auditBody(headers, req.getBody(), url, method, facade.getStatus(), true, true);
            }

        } catch (Exception e){
            ((ResponseFacade) servletResponse).setStatus(500);
            e.printStackTrace();
        }

    }



}
