package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    public User fromDto(UserCreateRequest request);

    public UserCreateResponse toDto(User user);
}
