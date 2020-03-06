package com.wipro.bartenders.users.api.audit;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@ControllerAdvice
@EnableWebMvc
public class Advice implements RequestBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    private String generateRequestId(){
        return String.format("%s", System.currentTimeMillis());
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        String body = new Scanner(httpInputMessage.getBody()).useDelimiter("\\A").next();

        HttpHeaders httpHeaders = httpInputMessage.getHeaders();
        Set<String> keys = httpHeaders.keySet();
        List<String> headers = new ArrayList<>();
        keys.forEach((key) -> headers.add(String.format("%s : %s", key, httpHeaders.get(key))));
        String rawHeaders = String.join("\n", headers);

        AuditLogRequest request = new AuditLogRequest();
        request.setHeaders(rawHeaders);
        request.setMethod("???");
        request.setEndpoint("??");
        request.setBody(body);
        request.setRequestId(generateRequestId());

        //new AuditLoggerTemplate().postRequest(request);

        return new HttpMessage(body, httpHeaders);
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    class HttpMessage implements HttpInputMessage {

        private InputStream body;
        private HttpHeaders headers;

        HttpMessage(String body, HttpHeaders headers){
            this.headers = headers;
            this.body = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
        }

        @Override
        public InputStream getBody() throws IOException {
            return this.body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }
    }
}
