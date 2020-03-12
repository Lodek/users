package com.wipro.bartenders.users.api.role.create;

import com.wipro.bartenders.users.api.common.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolesCreateRequest extends BaseRequest {
    @NotNull
    private String name;
}
