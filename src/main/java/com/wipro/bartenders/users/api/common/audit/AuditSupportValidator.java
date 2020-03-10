package com.wipro.bartenders.users.api.common.audit;

public interface AuditSupportValidator {

    boolean isSupported(String path, String method);
}
