package com.wipro.bartenders.users.api.common;

import lombok.Data;

@Data
public class BaseRequest {

    //ApiModelProperty(hidden = true)
    private String correlationId;

    //ApiModelProperty(hidden = true)
    private String mock;

    //ApiModelProperty(hidden = true)
    private String requestId;

    //ApiModelProperty(hidden = true)
    private String saveAuditData;

    //ApiModelProperty(hidden = true)
    private String userId;
}
