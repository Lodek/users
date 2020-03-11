package com.wipro.bartenders.users.api.common.audit;

import org.springframework.core.MethodParameter;

public interface AuditRequestBodyHandler {

    /**
     * Populate @RequestBody object (which extends BaseRequest) with data from the AuditRequestHeaders.
     */
    Object handleBody(AuditRequestHeaders auditRequestHeaders, Object body, MethodParameter methodParameter);
}
