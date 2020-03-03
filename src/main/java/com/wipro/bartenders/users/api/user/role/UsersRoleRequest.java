package com.wipro.bartenders.users.api.user.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UsersRoleRequest {
    @NotNull
    @Positive
    private Long roleId;
}
