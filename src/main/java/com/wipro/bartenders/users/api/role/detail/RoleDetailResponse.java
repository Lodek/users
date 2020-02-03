package com.wipro.bartenders.users.api.role.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDetailResponse {
    String name;
    Integer permissionLevel;
}