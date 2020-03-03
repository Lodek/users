package com.wipro.bartenders.users.api.role.update;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolesUpdateRequest {
    @NotNull
    private String name;
}
