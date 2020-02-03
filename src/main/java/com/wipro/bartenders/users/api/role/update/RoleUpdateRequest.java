package com.wipro.bartenders.users.api.role.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateRequest {
    String name;
    Integer permissionLevel;
}
