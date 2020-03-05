package com.wipro.bartenders.users.api.common.audit;

public class AuditBodyUtil {
    getHeaderValue(HttpHeaders, String)

    getResponseStatusCode(ServerHttpResponse)

    isAuditEnable(HttpHeaders)

    isAuditEnabled(HttpHeaders)

    parseToJsonStringWithoutIgnoredFields(Object, List<String>)

    readBody(HttpServletRequest)

    removeIgnoredFields(JsonNode, List<String>)

    removeIgnoredFields(String, List<String>)

    toAuditRequestHeaders(HttpHeaders, AuditRequestHeadersBuilder)

}
