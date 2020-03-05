package com.wipro.bartenders.users.api.common.audit;

import javafx.beans.DefaultProperty;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.AccessType;

@Configuration
public class AuditConfiguration {
    //@Value("${data.audit.defaultSaveAuditData:true}")

    defaultSaveAuditData : String

    auditBodyAction(AuditBodyService, AuditSupportValidator)

    auditIgnoredFields()

    auditRequestBodyHandler()

    auditRequestHeadersBuilder()

    auditSupportValidator(AuditBodyService)

}
