package com.wipro.bartenders.users.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class User {

    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String email;

    public User(long id, String userName, String firstName, String lastName, String birthDate, String email) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public User update(User newUser) {
        User clone = null;
        try {
            clone = (User) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        String firstName = newUser.getFirstName();
        if (!firstName.isEmpty())
            clone.setFirstName(firstName);

        String userName = newUser.getUserName();
        if (!userName.isEmpty())
            clone.setUserName(userName);

        String lastName = newUser.getLastName();
        if (!lastName.isEmpty())
            clone.setLastName(lastName);

        String birthDate = newUser.getBirthDate();
        if (!birthDate.isEmpty())
            clone.setFirstName(birthDate);

        String email = newUser.getEmail();
        if(email.isEmpty())
            clone.setFirstName(email);

        return clone;
    }
}
