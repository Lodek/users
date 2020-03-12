package com.wipro.bartenders.users.api.role.update;

import com.wipro.bartenders.users.api.common.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolesUpdateRequest extends BaseRequest {
    @NotNull
    private String name;
}
