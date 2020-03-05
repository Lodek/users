package com.wipro.bartenders.users.api.common.audit;

import javax.servlet.http.HttpServletRequestWrapper;

public class AuditRequestWrapper extends HttpServletRequestWrapper {
    auditEnable :boolean

    auditRequestHeaders :AuditRequestHeaders

    body :String

    headerMap :Map<String, String>

    class InnerClass {
        public ServletInputStream getInputStream () throws IOException {

        }
    }
}
