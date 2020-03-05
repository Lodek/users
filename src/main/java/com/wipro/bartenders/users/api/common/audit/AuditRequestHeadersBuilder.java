package com.wipro.bartenders.users.api.common.audit;

public interface AuditRequestHeadersBuilder {

    AuditRequestHeaders build(String simulated, String userId, String requestId, String correlationId, String saveAuditData);
}
