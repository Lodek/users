package com.wipro.bartenders.users.api.common.audit;

import com.wipro.bartenders.users.api.common.BaseRequest;
import com.wipro.bartenders.users.api.common.logger.LoggerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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



    @Bean(name="auditIgnoredFields")
    List<String> auditIgnoredFields(){
        return Stream.of(AuditIgnoredFieldsEnum.values())
                .map(AuditIgnoredFieldsEnum::field)
                .collect(Collectors.toList());
    }

    @Bean
    public AuditRequestBodyHandler auditRequestBodyHandler(){
        return new AuditRequestBodyHandler() {
            @Override
            public Object handleBody(AuditRequestHeaders auditRequestHeaders, Object body, MethodParameter methodParameter) {
                /**
                 * Populate @RequestBody object (which extends BaseRequest) with data from the AuditRequestHeaders.
                 */
                try {
                    if (body == null) {
                        body = methodParameter.getParameterType().newInstance();
                    }
                } catch (Exception e) {
                    System.err.print(e.getStackTrace());
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

    @Bean
    public AuditRequestHeadersBuilder auditRequestHeadersBuilder(){
        return new AuditRequestHeadersBuilder() {
            @Override
            public AuditRequestHeaders build(String simulated, String userId, String requestId, String correlationId, String saveAuditData) {
                return AuditRequestHeaders.builder()
                        .simulated(defaultIfEmpty(simulated, AuditConstants.SIMULATED))
                        .saveAuditData(defaultIfEmpty(saveAuditData, defaultSaveAuditData))
                        .userId(userId)
                        .requestId(requestId)
                        .correlationId(correlationId)
                        .build();
            }
        };
    }


    @Bean
    public AuditSupportValidator auditSupportValidator(AuditSupportValidator validator){
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

}
