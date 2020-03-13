package com.wipro.bartenders.users.api.common.audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration
public class AuditHeadersArgumentResolverConfig implements WebMvcConfigurer {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuditHeadersArgumentResolver());
    }


    public class AuditHeadersArgumentResolver implements HandlerMethodArgumentResolver{

        @Override
        public boolean supportsParameter(MethodParameter param) {
            return param.getParameterType().equals(AuditRequestHeaders.class);
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
            AuditRequestWrapper request = (AuditRequestWrapper) nativeWebRequest.getNativeRequest();
            return request.getAuditRequestHeaders();
        }
    }

}
