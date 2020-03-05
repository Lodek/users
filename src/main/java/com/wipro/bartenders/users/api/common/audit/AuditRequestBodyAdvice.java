package com.wipro.bartenders.users.api.common.audit;

public class AuditRequestBodyAdvice {
    auditRequestBodyHandler : AuditRequestBodyHandler

    auditRequestHeadersBuilder : AuditRequestHeadersBuilder

    handleBody(Object, HttpInputMessage, MethodParameter): Object

}
