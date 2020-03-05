package com.wipro.bartenders.users.api.common.audit;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class AuditHeadersArgumentResolverConfig implements WebMvcConfigurer {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuditHeadersArgumentResolver());
    }


    public class AuditHeadersArgumentResolver implements HandlerMethodArgumentResolver{

    }

}
