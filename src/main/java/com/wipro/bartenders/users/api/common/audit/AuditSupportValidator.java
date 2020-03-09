package com.wipro.bartenders.users.api.common.audit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuditSupportValidator {

    private static Map<String, Set<String>> supported;

    {
        initEndpoints();
    }

    public boolean isSupported(String path, String method){
        Set<String> methods = supported.get(path);
        if (methods == null ){
            return false;
        }
        else {
            return methods.contains(method);
        }
    }

    private static void initEndpoints(){
        Map<String, Set<String>> supported = new HashMap<>();

        supported.put("/roles",
                setOf(HttpMethods.GET.value, HttpMethods.PUT.value, HttpMethods.POST.value));


        supported.put("/users",
                setOf(HttpMethods.GET.value, HttpMethods.PUT.value, HttpMethods.POST.value));

    }

    private static <T> Set<T> setOf(T ... args){
        Set<T> set = new HashSet<>();
        for (T t : args){
            set.add(t);
        }
        return set;
    }

    enum HttpMethods {
        POST("POST"),
        GET("GET"),
        PUT("PUT");

        public final String value;

        HttpMethods(String method) {
            this.value = method;
        }
    }

}
