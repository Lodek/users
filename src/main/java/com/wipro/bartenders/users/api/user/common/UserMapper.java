package com.wipro.bartenders.users.api.user.common;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDto(UsersDto request);

    UsersIdDto toIdDto(User user);

    UsersDetailsDto toDetailsDto(User user);

}
