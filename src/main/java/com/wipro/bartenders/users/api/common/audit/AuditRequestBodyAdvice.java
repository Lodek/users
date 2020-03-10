package com.wipro.bartenders.users.api.common.audit;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@ControllerAdvice
public class AuditRequestBodyAdvice implements RequestBodyAdvice {

    AuditRequestBodyHandler auditRequestBodyHandler;

    AuditRequestHeadersBuilder auditRequestHeadersBuilder;

    public Object handleBody(Object o, HttpInputMessage inputMessage, MethodParameter param){
        HttpHeaders headers = inputMessage.getHeaders();
        AuditRequestHeaders auditHeaders = auditRequestHeadersBuilder.build(headers.getFirst(AuditRequestHeadersConstants.HEADER_SIMULATE),
                headers.getFirst(AuditRequestHeadersConstants.HEADER_USER_ID),
                headers.getFirst(AuditRequestHeadersConstants.HEADER_RQST_ID),
                headers.getFirst(AuditRequestHeadersConstants.HEADER_CORRELATION_ID),
                headers.getFirst(AuditRequestHeadersConstants.HEADER_SAVE_AUDIT_DATA));
        return auditRequestBodyHandler.handleBody(auditHeaders, o, param);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return handleBody(o, httpInputMessage, methodParameter);
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return handleBody(o, httpInputMessage, methodParameter);
    }
}
