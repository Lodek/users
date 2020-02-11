package com.wipro.bartenders.users.utils;

import com.wipro.bartenders.users.domain.user.User;

import java.time.LocalDate;

public class UserTestUtils {
    public static User getBob(Long id){
        String first = "Bob";
        String last = "Ross";
        String userName = "bob.ross";
        String email = "bob.ross@email.com";
        LocalDate dob = LocalDate.of(2000, 11, 30);
        User u = new User(id, userName, first, last, dob, email);
        return u;
    }
}
