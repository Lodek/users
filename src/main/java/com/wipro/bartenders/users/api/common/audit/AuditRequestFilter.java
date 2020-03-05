package com.wipro.bartenders.users.api.common.audit;

public class AuditRequestFilter {
    auditBodyAction : AuditBodyAction

    auditIgnoredFields : List<String>

    auditRequestHeadersBuilder : AuditRequestHeadersBuilder

    auditSupportValidator : AuditSupportValidator

    hexResourceIdGenerator : ResourceIdGenerator

}
