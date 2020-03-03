package com.wipro.bartenders.users.api.user.update;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class UsersUpdateRequest {
    @Nullable
    @Email
    private String email;

    private String userName;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;
}
