package com.wipro.bartenders.users.api.common.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfiguration {

    //@Value("${data.audit.defaultSaveAuditData:true}")
    String defaultSaveAuditData;

    @Bean
    AuditBodyAction auditBodyAction(AuditBodyService serv, AuditSupportValidator validator){
        return null;
    }

    AuditIgnoredFieldsEnum auditIgnoredFields(){
        return null;
    }

    @Bean
    AuditRequestBodyHandler auditRequestBodyHandler(){
        return null;
    }

    AuditRequestHeadersBuilder auditRequestHeadersBuilder(){
        return null;
    }


    @Bean
    AuditSupportValidator auditSupportValidator(AuditBodyService serv){
        return null;
    }

    private class AuditBodyService {
    }
}
