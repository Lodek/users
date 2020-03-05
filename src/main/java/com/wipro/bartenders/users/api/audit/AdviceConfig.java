package com.wipro.bartenders.users.api.audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@Configuration
@EnableWebMvc
public class AdviceConfig extends WebMvcConfigurationSupport {

    private RequestBodyAdvice advice;
}
