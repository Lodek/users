package com.wipro.bartenders.users.api.loggable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;

import java.time.LocalTime;

@Aspect
public class Logger {

    private static final String loggerUrl = "http://localhost:8080";
    private static final String logRequestUrl = loggerUrl + "/reqquests";
    private static final String logResponseUrl = loggerUrl + "/responses";


    @Before()
    void logRequest(Object request) {}

    @After()
    void logResponse(Object response) {}

    @After()
    void logMessage(Loggable loggable, String requestHash) {}

    @AfterThrowing()
    void logException() {}

    @Around( )
    void logFlow(ProceedingJoinPoint jp){
        try{
            Time.
        }
        catch (Exception e){

        }
    }

}
