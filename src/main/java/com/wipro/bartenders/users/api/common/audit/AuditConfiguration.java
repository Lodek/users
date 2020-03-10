package com.wipro.bartenders.users.api.common.audit;

import com.wipro.bartenders.users.api.common.BaseRequest;
import com.wipro.bartenders.users.api.common.logger.LoggerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;

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

    @Bean
    AuditRequestBodyHandler auditRequestBodyHandler(){
        return new AuditRequestBodyHandler() {
            @Override
            public Object handleBody(AuditRequestHeaders auditRequestHeaders, Object body, MethodParameter methodParameter) {
                try {
                    if (body == null) {
                        body = methodParameter.getParameterType().newInstance();
                    }
                } catch (Exception e) {

                }
                BaseRequest obj = (BaseRequest) body;
                obj.setCorrelationId(auditRequestHeaders.getCorrelationId());
                obj.setMock(auditRequestHeaders.getSimulated());
                obj.setRequestId(auditRequestHeaders.getRequestId());
                obj.setSaveAuditData(auditRequestHeaders.getSaveAuditData());
                obj.setUserId(auditRequestHeaders.getUserId());
                return obj;
            }
        };
    }

    AuditRequestHeadersBuilder auditRequestHeadersBuilder(){
        return null;
    }


    @Bean
    AuditSupportValidator auditSupportValidator(AuditSupportValidator validator){
        return new AuditSupportValidator();
    }

}
