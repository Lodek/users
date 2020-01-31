package com.wipro.bartenders.users.api.user.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponse{
    //A lot of code repetition
    //kinda lame
    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String email;
}