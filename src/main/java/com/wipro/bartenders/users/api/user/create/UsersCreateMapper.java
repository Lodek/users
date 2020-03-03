package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersCreateMapper {

    User userFromDto(UsersCreateRequest request);
    UsersCreateResponse usersToDto(User user);
}
