package com.wipro.bartenders.users.api.common.logger;

import org.springframework.stereotype.Component;

@Component
public class LoggerService {

    public void logRequest(String body, String requestId, String headers, String resourcePath, String method){

    }
    public void logResponse(String requestId, int responseStatusCode){

    }
    public void logMessage(){

    }
}
