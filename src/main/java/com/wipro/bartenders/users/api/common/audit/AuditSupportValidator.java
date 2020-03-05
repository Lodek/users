package com.wipro.bartenders.users.api.common.audit;

public interface AuditSupportValidator {

    boolean isSuported(String path, String method);
}
