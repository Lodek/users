package com.wipro.bartenders.users.api.common.audit;

public interface AuditBodyAction {
    void auditBody(AuditRequestHeaders auditRequestHeaders, String body, String requestPath, String requestHttpMethod, int responseStatusCode, boolean isRequestType, boolean isParentRequest);
}
