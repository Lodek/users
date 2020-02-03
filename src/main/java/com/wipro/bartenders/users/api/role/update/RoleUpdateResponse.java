package com.wipro.bartenders.users.api.role.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateResponse {
    String name;
    Integer permissionLevel;
}
