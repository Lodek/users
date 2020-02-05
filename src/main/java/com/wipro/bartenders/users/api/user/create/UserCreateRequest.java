package com.wipro.bartenders.users.api.user.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class UserCreateRequest {
    //request DTO does not allow user to set ID

    private String userName;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String email;

    public LocalDate parseDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(this.getBirthDate(), formatter);
    }
}
