package com.wipro.bartenders.users.api.common.audit;

public interface AuditRequestBodyHandler {
    handleBody(AuditRequestHeaders auditRequestHeaders, Object body, MethodParameter methodParameter): Object
}
