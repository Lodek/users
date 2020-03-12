package com.wipro.bartenders.users.api.common.audit;

import com.wipro.bartenders.users.api.common.BaseRequest;
import com.wipro.bartenders.users.api.common.logger.LoggerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.wipro.bartenders.users.api.common.audit.AuditBodyUtil.defaultIfEmpty;
import static com.wipro.bartenders.users.api.common.audit.AuditBodyUtil.setOf;

@Configuration
public class AuditConfiguration {

    @Value("${data.audit.defaultSaveAuditData:true}")
    String defaultSaveAuditData;


    @Bean
    public AuditBodyAction auditBodyAction(LoggerService logger, AuditSupportValidator validator){
        return (auditRequestHeaders, body, requestPath, requestHttpMethod, responseStatusCode, isRequestType, isParentRequest) -> {
            if (validator.isSupported(requestPath, requestHttpMethod)){
                logger.logRequest(body, auditRequestHeaders.getRequestId(), "", requestPath, requestHttpMethod);
                logger.logResponse(auditRequestHeaders.getRequestId(), responseStatusCode);
            }
        };
    }



    @Bean(name="auditIgnoredFields")
    List<String> auditIgnoredFields(){
        return Stream.of(AuditIgnoredFieldsEnum.values())
                .map(AuditIgnoredFieldsEnum::field)
                .collect(Collectors.toList());
    }

    @Bean
    public AuditRequestBodyHandler auditRequestBodyHandler(){
        return (auditRequestHeaders, body, methodParameter) -> {
           try {
                if (body == null) {
                    body = methodParameter.getParameterType().newInstance();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseRequest obj = (BaseRequest) body;
            obj.setCorrelationId(auditRequestHeaders.getCorrelationId());
            obj.setMock(auditRequestHeaders.getSimulated());
            obj.setRequestId(auditRequestHeaders.getRequestId());
            obj.setSaveAuditData(auditRequestHeaders.getSaveAuditData());
            obj.setUserId(auditRequestHeaders.getUserId());
            return obj;
        };
    }

    @Bean
    public AuditRequestHeadersBuilder auditRequestHeadersBuilder(){
        return (simulated, userId, requestId, correlationId, saveAuditData) -> AuditRequestHeaders.builder()
                .simulated(defaultIfEmpty(simulated, "false"))
                .saveAuditData(defaultIfEmpty(saveAuditData, defaultSaveAuditData))
                .userId(defaultIfEmpty(userId, "bob"))
                .requestId(requestId)
                .correlationId(defaultIfEmpty(correlationId, "0"))
                .build();
    }


    @Bean
    public AuditSupportValidator auditSupportValidator(){
        return new AuditSupportValidator(){

            private Map<String, Set<String>> endpoints = new HashMap<>();

            {
                endpoints.put("/roles",
                        setOf(RequestMethod.GET.name() , RequestMethod.PUT.name(), RequestMethod.POST.name()));
                endpoints.put("/users",
                        setOf(RequestMethod.GET.name(), RequestMethod.PUT.name(), RequestMethod.POST.name()));
            }

            @Override
            public boolean isSupported(String path, String method) {
                Set<String> methods = endpoints.get(path);
                if (methods == null ){
                    return false;
                }
                else {
                    return methods.contains(method);
                }
            }
        };
    }

    @Bean
    public ResourceIdGenerator resourceIdGenerator(){
        return (request) -> Long.toString(System.currentTimeMillis());
    }

}
