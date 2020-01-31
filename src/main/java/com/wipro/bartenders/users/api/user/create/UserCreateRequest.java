package com.wipro.bartenders.users.api.user.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    //request DTO does not allow user to set ID

    private String userName;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String email;
}
