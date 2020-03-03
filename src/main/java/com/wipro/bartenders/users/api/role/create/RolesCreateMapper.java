package com.wipro.bartenders.users.api.role.create;

import com.wipro.bartenders.users.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesCreateMapper {
    Role roleFromRequest(RolesCreateRequest request);
    RolesCreateResponse roleToResponse(Role role);
}
