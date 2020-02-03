package com.wipro.bartenders.users.api.role.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleCreateRequest {
    //request DTO does not allow user to set ID

    private String name;
    private Integer permissionLevel;

}
