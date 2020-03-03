package com.wipro.bartenders.users.api.user.update;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsersUpdateResponse {
    private Long id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
