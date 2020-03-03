package com.wipro.bartenders.users.api.role.list;

import com.wipro.bartenders.users.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesListMapper {
    RolesListResponse roleToResponse(Role role);
}
