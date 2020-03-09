package com.wipro.bartenders.users.api.common.audit;

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

    AuditBodyAction auditBodyAction;

    List<String> auditIgnoredFields;

    AuditRequestHeadersBuilder auditRequestHeadersBuilder;

    AuditSupportValidator auditSupportValidator;

    //ResourceIdGenerator hexResourceIdGenerator;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        boolean isSupported = auditSupportValidator.isSupported(request.getMethod(), request.getRequestURI());

        String auditEnable = request.getHeader(AuditRequestHeadersConstants.HEADER_AUDIT_ENABLED);
        String correlationId = request.getHeader(AuditRequestHeadersConstants.HEADER_CORRELATION_ID);
        String requestId = request.getHeader(AuditRequestHeadersConstants.HEADER_RQST_ID);
        String saveAuditData = request.getHeader(AuditRequestHeadersConstants.HEADER_SAVE_AUDIT_DATA);
        String simulate = request.getHeader(AuditRequestHeadersConstants.HEADER_SIMULATE);
        String userId = request.getHeader(AuditRequestHeadersConstants.HEADER_USER_ID);

        AuditRequestHeaders auditRequestHeaders = auditRequestHeadersBuilder.build(simulate, userId, requestId, correlationId, saveAuditData);

    }



}
