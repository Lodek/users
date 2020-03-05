package com.wipro.bartenders.users.api.common.audit;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

public class AuditRequestBodyAdvice implements RequestBodyAdvice {

    AuditRequestBodyHandler auditRequestBodyHandler;

    AuditRequestHeadersBuilder auditRequestHeadersBuilder;

    Object handleBody(Object o, HttpInputMessage inputMessage, MethodParameter param);

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return null;
    }
}
