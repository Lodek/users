package com.wipro.bartenders.users.api.common.audit;

import com.wipro.bartenders.users.api.common.logger.LoggerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfiguration {

    //@Value("${data.audit.defaultSaveAuditData:true}")
    String defaultSaveAuditData;


    @Bean
    AuditBodyAction auditBodyAction(LoggerService logger, AuditSupportValidator validator){
        return new AuditBodyAction() {
            @Override
            public void auditBody(AuditRequestHeaders auditRequestHeaders, String body, String requestPath, String requestHttpMethod, int responseStatusCode, boolean isRequestType, boolean isParentRequest) {
                if (validator.isSupported(requestPath, requestHttpMethod)){
                    logger.logRequest(body, auditRequestHeaders.getRequestId(), "", requestPath, requestHttpMethod);
                    logger.logResponse(auditRequestHeaders.getRequestId(), responseStatusCode);
                }
            }
        };
    }

    AuditIgnoredFieldsEnum auditIgnoredFields(){
        return null;
    }

    AuditRequestBodyHandler auditRequestBodyHandler(){
        return null;
    }

    AuditRequestHeadersBuilder auditRequestHeadersBuilder(){
        return null;
    }


    AuditSupportValidator auditSupportValidator(AuditSupportValidator validator){
        return null;
    }

}
