package com.wipro.bartenders.users.api.common.audit;

import lombok.Data;

@Data
public class AuditRequestHeaders {
    private String correlationId;

    private String requestId;

    private String saveAuditData;

    private String simulated;

    private String userId;

}
