package com.wipro.bartenders.users.api.user.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UsersDto {
    private String userName;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String email;
}
