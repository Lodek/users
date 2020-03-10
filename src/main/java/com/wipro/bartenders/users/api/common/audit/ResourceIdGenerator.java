package com.wipro.bartenders.users.api.common.audit;

import javax.servlet.http.HttpServletRequest;

public class ResourceIdGenerator {
    String getId(HttpServletRequest request){
        return Long.toString(System.currentTimeMillis());
    }
}
