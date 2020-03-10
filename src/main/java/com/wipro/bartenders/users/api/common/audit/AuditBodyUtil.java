package com.wipro.bartenders.users.api.common.audit;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AuditBodyUtil {
    public static String getHeaderValue(HttpHeaders headers, String name){
        return String.join(",", headers.get(name));
    }

    public static int getResponseStatusCode(ServerHttpResponse response){
        return response.getStatusCode().value();
    }

    public static boolean isAuditEnable(HttpHeaders headers){
        String value = getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_AUDIT_ENABLED);
        return value.equals(AuditConstants.TRUE_SW);
    }

    public static boolean isAuditEnabled(HttpHeaders headers){
        String value = getHeaderValue(headers, AuditRequestHeadersConstants.HEADER_AUDIT_ENABLED);
        return value.equals(AuditConstants.TRUE_SW);
    }

    public static String parseToJsonStringWithoutIgnoredFields(Object o, List<String> ignoredFields){
        return null;
    }

    public static String readBody(HttpServletRequest request) throws IOException {
        Scanner s = new Scanner(request.getInputStream());
        return s.useDelimiter("\\A").next();
    }

    public static JsonNode removeIgnoredFields(JsonNode json, List<String> ignoredFields){
        return null;
    }

    public static String removeIgnoredFields(String json, List<String> ignoredFields){
        return null;
    }

    public static AuditRequestHeaders toAuditRequestHeaders(HttpHeaders headers, AuditRequestHeadersBuilder builder){
        return null;
    }

    public static String defaultIfEmpty(String target, String backup){
        return (target == null || target.isEmpty()) ? backup : target;
    }

    public static <T> Set<T> setOf(T ... args){
        Set<T> set = new HashSet<>();
        for (T t : args){
            set.add(t);
        }
        return set;
    }


}
