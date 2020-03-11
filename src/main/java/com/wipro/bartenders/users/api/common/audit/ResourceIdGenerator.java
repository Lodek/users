package com.wipro.bartenders.users.api.common.audit;

import javax.servlet.http.HttpServletRequest;

public interface ResourceIdGenerator {
    String getId(HttpServletRequest request);
}
