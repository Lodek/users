package com.wipro.bartenders.users.api.role.common;

import com.wipro.bartenders.users.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role fromDto(RolesDto dto);
    RolesDto toDto(Role role);
    RolesDetailsDto toDetailsDto(Role role);
    RolesIdDto toRoleIdDto(Role role);
}
