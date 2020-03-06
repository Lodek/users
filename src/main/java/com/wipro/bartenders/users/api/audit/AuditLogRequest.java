package com.wipro.bartenders.users.api.audit;

import lombok.Data;

@Data
public class AuditLogRequest {

    private String requestId;
    private String body;
    private String headers;
    private String method;
    private String endpoint;
}
