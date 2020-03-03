package com.wipro.bartenders.users.api.role.update;

import com.wipro.bartenders.users.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesUpdateMapper {
    RolesUpdateResponse roleToResponse(Role role);
    Role roleFromRequest(RolesUpdateRequest role);
}
