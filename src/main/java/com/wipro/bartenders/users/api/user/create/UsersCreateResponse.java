package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.api.user.common.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersCreateResponse extends UserDto {
    Long id;
}
