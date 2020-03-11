package com.wipro.bartenders.users.api.common.audit;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

@Getter
@Setter
public class AuditRequestWrapper extends HttpServletRequestWrapper {

    private boolean auditEnable;

    private AuditRequestHeaders auditRequestHeaders;

    private String body;

    private Map<String, String> headerMap;


    public AuditRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = AuditBodyUtil.readBody(request);
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new AuditInputStream(this.body.getBytes());
    }

    class AuditInputStream extends ServletInputStream {

        private byte[] bytes;

        AuditInputStream(byte[] bytes){
            super();
            this.bytes = bytes;
        }


        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return 0;
        }
    }
}
