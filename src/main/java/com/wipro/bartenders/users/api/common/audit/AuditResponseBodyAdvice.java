package com.wipro.bartenders.users.api.common.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

import static com.wipro.bartenders.users.api.common.audit.AuditBodyUtil.toAuditRequestHeaders;

@ControllerAdvice
public class AuditResponseBodyAdvice implements ResponseBodyAdvice {

    @Autowired
    AuditBodyAction auditBodyAction;

    @Autowired
    List<String> auditIgnoredFields;

    @Autowired
    AuditRequestHeadersBuilder builder;


    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse response) {
        AuditRequestHeaders auditHeaders = toAuditRequestHeaders(request.getHeaders(), builder);
        HttpHeaders responseHeaders = response.getHeaders();
        responseHeaders.set(AuditRequestHeadersConstants.HEADER_CORRELATION_ID, auditHeaders.getCorrelationId());
        responseHeaders.set(AuditRequestHeadersConstants.HEADER_RQST_ID, auditHeaders.getRequestId());
        responseHeaders.set(AuditRequestHeadersConstants.HEADER_SAVE_AUDIT_DATA, auditHeaders.getSaveAuditData());
        responseHeaders.set(AuditRequestHeadersConstants.HEADER_SIMULATE, auditHeaders.getSimulated());
        responseHeaders.set(AuditRequestHeadersConstants.HEADER_USER_ID, auditHeaders.getUserId());
        return o;
    }
}
