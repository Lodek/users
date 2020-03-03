package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersListMapper {
    UsersListResponse userToResponse(User user);
}
