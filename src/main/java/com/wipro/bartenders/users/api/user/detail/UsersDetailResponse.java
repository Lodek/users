package com.wipro.bartenders.users.api.user.detail;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsersDetailResponse {
    Long id;
    String userName;
    String firstName;
    String lastName;
    String email;
    LocalDate birthDate;
    List<Long> roles;
}
