package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.api.common.BaseRequest;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UsersCreateRequest extends BaseRequest {

    @NotNull
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @Past
    private LocalDate birthDate;
}
