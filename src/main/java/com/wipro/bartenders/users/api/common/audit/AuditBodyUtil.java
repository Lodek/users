package com.wipro.bartenders.users.api.common.audit;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuditBodyUtil {
    public String getHeaderValue(HttpHeaders headers, String name){
        return headers.getFirst(name);
    }

    public int getResponseStatusCode(ServerHttpResponse response){
        return response.getStatusCode().value();
    }

    public boolean isAuditEnable(HttpHeaders headers){
        String value = getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_AUDIT_ENABLED);
        return value.equals(AuditConstants.TRUE_SW);
    }

    public boolean isAuditEnabled(HttpHeaders headers){
        String value = getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_AUDIT_ENABLED);
        return value.equals(AuditConstants.TRUE_SW);
    }

    String parseToJsonStringWithoutIgnoredFields(Object o, List<String> ignoredFields){
        return null;
    }

    public String readBody(HttpServletRequest request){
        return null;
    }

    public JsonNode removeIgnoredFields(JsonNode json, List<String> ignoredFields){
        return null;
    }

    public String removeIgnoredFields(String json, List<String> ignoredFields){
        return null;
    }

    public AuditRequestHeaders toAuditRequestHeaders(HttpHeaders headers, AuditRequestHeadersBuilder builder){
        return null;
    }

}
