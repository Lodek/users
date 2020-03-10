package com.wipro.bartenders.users.api.common.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

import static com.wipro.bartenders.users.api.common.audit.AuditBodyUtil.getHeaderValue;

@ControllerAdvice
public class AuditRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    AuditRequestBodyHandler auditRequestBodyHandler;

    @Autowired
    AuditRequestHeadersBuilder auditRequestHeadersBuilder;

    public Object handleBody(Object o, HttpInputMessage inputMessage, MethodParameter param){
        HttpHeaders headers = inputMessage.getHeaders();
        AuditRequestHeaders auditHeaders = auditRequestHeadersBuilder.build(getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_SIMULATE),
                getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_USER_ID),
                getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_RQST_ID),
                getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_CORRELATION_ID),
                getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_SAVE_AUDIT_DATA));

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
