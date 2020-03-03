package com.wipro.bartenders.users.api.role.detail;

import com.wipro.bartenders.users.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesDetailMapper {
    RolesDetailResponse roleToResponse(Role role);
}
