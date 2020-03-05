package com.wipro.bartenders.users.api.common.audit;

public class AuditConfiguration {
    @Value("${data.audit.defaultSaveAuditData:true}")

    defaultSaveAuditData : String

    auditBodyAction(AuditBodyService, AuditSupportValidator)

    auditIgnoredFields()

    auditRequestBodyHandler()

    auditRequestHeadersBuilder()

    auditSupportValidator(AuditBodyService)

}
