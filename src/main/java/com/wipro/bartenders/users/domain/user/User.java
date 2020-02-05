package com.wipro.bartenders.users.domain.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    private String email;

    public User(long id, String userName, String firstName, String lastName, LocalDate birthDate, String email) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public User(String userName, String firstName, String lastName, LocalDate birthDate, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public static User emptyUser(){
        return new User();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(email, user.email);
    }


    public User update(User newUser) {

        if (newUser.userName != null && !newUser.userName.isEmpty())
            this.setUserName(newUser.userName);

        if (newUser.firstName != null && !newUser.firstName.isEmpty())
            this.setFirstName(newUser.firstName);

        if (newUser.lastName != null && !newUser.lastName.isEmpty())
            this.setLastName(newUser.lastName);

        if (newUser.id != null)
            this.setId(newUser.id);

        if (newUser.email != null && !newUser.email.isEmpty())
            this.setEmail(newUser.email);

        if (newUser.birthDate != null)
            this.setBirthDate(newUser.birthDate);

        return this;
    }


    public boolean compareProperty(User a, User b, String propertyName) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        //Assume user a and b are both of type User
        //Uses reflection to reduce repetition in property comparision
            //PropertyDescriptor pd = new PropertyDescriptor(propertyName, User.class);
            //Method method = pd.getReadMethod();
            //Class returnType = method.getReturnType();
            //Object aValuee = method.invoke(a);
            //Object bValuee = method.invoke(b);
            //returnType.cast(aValuee);
        return true;
    }

}
