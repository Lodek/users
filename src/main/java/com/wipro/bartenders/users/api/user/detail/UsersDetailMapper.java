package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersDetailMapper {
    UsersDetailResponse userToResponse(User user);
}
