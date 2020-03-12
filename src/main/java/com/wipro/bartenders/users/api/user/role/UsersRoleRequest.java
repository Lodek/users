package com.wipro.bartenders.users.api.user.role;

import com.wipro.bartenders.users.api.common.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UsersRoleRequest extends BaseRequest {
    @NotNull
    @Positive
    private Long roleId;
}
