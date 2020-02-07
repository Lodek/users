package com.wipro.bartenders.users.api.user.detail;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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