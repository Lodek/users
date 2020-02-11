package com.wipro.bartenders.users.api.role.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RolesDto {
    String name;
    Integer permissionLevel;
}
