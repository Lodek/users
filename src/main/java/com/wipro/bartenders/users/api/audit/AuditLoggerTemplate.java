package com.wipro.bartenders.users.api.audit;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class AuditLoggerTemplate {

    private static final String loggerUrl = "http://localhost:8090";
    private static final String requestsEndpoint = loggerUrl + "/requests";
    private static final String responsesEndpoint = loggerUrl + "/responses";
    private final RestTemplate template = new RestTemplate();

    public void postRequest(AuditLogRequest r) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuditLogRequest> request = new HttpEntity<>(r, headers);
        template.postForObject(requestsEndpoint, request, AuditLogRequest.class);

    }

    public void postResponse() {

    }

}
