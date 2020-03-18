package com.wipro.bartenders.users.api.common.audit;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.wipro.bartenders.users.api.common.audit.AuditBodyUtil.joinHeaders;
import static com.wipro.bartenders.users.api.common.audit.AuditBodyUtil.setOf;

public class AuditRequestWrapper extends HttpServletRequestWrapper {

    private boolean auditEnable;

    @Getter
    private AuditRequestHeaders auditRequestHeaders;

    @Getter
    @Setter
    private String body;

    private Map<String, String> headerMap;

    public AuditRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = AuditBodyUtil.readBody(request);

        this.headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            this.headerMap.put(name, joinHeaders(request.getHeaders(name)));
        }

    }

    public void setAuditRequestHeaders(AuditRequestHeaders headers){
        headerMap.put(AuditRequestHeadersConstants.HEADER_SIMULATE, headers.getSimulated());
        headerMap.put(AuditRequestHeadersConstants.HEADER_AUDIT_ENABLED, headers.getSaveAuditData());
        headerMap.put(AuditRequestHeadersConstants.HEADER_CORRELATION_ID, headers.getCorrelationId());
        headerMap.put(AuditRequestHeadersConstants.HEADER_RQST_ID, headers.getRequestId());
        headerMap.put(AuditRequestHeadersConstants.HEADER_SAVE_AUDIT_DATA, headers.getSaveAuditData());
        headerMap.put(AuditRequestHeadersConstants.HEADER_USER_ID, headers.getUserId());
        this.auditRequestHeaders = headers;
    }

    public void setHeader(String name, String value){
        this.headerMap.put(name, value);
    }

    @Override
    public Enumeration<String> getHeaders(String name){
        return Collections.enumeration(setOf(this.getHeader(name)));
    }

    @Override
    public String getHeader(String headerName){
        return this.headerMap.get(headerName);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(this.headerMap.keySet());
    }


    @Override
    public ServletInputStream getInputStream() {
        return new AuditInputStream(this.body);
    }

    class AuditInputStream extends ServletInputStream  {

        private InputStream stream;

        AuditInputStream(String body){
            super();
            this.stream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
        }


        @SneakyThrows
        @Override
        public boolean isFinished() {
            return this.stream.available() > 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
        }

        @Override
        public int read() throws IOException {
            return this.stream.read();
        }
    }
}
