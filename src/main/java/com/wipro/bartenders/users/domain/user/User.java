package com.wipro.bartenders.users.domain.user;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;


@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String userName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String birthDate;

    @Column
    private String email;

    public User(long id, String userName, String firstName, String lastName, String birthDate, String email) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public User(String userName, String firstName, String lastName, String birthDate, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public User(){ }

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
        String firstName = newUser.getFirstName();
        if (firstName != null && !firstName.isEmpty())
            this.setFirstName(firstName);

        String userName = newUser.getUserName();
        if (userName != null && !userName.isEmpty())
            this.setUserName(userName);

        String lastName = newUser.getLastName();
        if (lastName != null && !lastName.isEmpty())
            this.setLastName(lastName);

        String birthDate = newUser.getBirthDate();
        if (birthDate != null && !birthDate.isEmpty())
            this.setFirstName(birthDate);

        String email = newUser.getEmail();
        if(email != null && email.isEmpty())
            this.setFirstName(email);

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
