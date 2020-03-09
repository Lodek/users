package com.wipro.bartenders.users.api.common.audit;

        import lombok.Getter;
        import lombok.Setter;

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

    public AuditRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }
}
