package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.api.common.BaseRequest;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class UsersUpdateRequest extends BaseRequest {
    @Nullable
    @Email
    private String email;

    private String userName;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;
}
