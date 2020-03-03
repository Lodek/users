package com.wipro.bartenders.users.api.role.create;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolesCreateRequest {
    @NotNull
    private String name;
}
