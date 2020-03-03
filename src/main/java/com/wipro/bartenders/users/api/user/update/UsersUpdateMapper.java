package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersUpdateMapper {

    User userFromRequest(UsersUpdateRequest request);
    UsersUpdateResponse userToResponse(User user);
}
