package com.wipro.bartenders.users.api.common.audit;

public class AuditHeadersArgumentResolverConfig {
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(new AuditHeadersArgumentResolver());

    }


    public class AuditHeadersArgumentResolver implements HandlerMethodArgumentResolver{

    }

}
