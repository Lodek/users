package com.wipro.bartenders.users.domain.user;


import com.wipro.bartenders.users.domain.post.Post;
import com.wipro.bartenders.users.domain.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

//TODO: add bean validation

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    {
        this.roles = new HashSet<>();
        this.posts = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    //TODO: figure out JPA many to many join tables
    //how to do query since this without double join or join table
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    @OneToMany(mappedBy = "op")
    private Set<Post> posts;


    public User(long id, String userName, String firstName, String lastName, LocalDate birthDate, String email) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.roles = new HashSet<>();
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

    public void appendRole(Role role){
        Set<Role> roles = this.getRoles();
        if (!roles.contains(role)){
            roles.add(role);
            role.getUsers().add(this);
        }
    }

    public void appendRoles(Set<Role> roles) {
        for (Role r : roles){
            this.appendRole(r);
        }
    }

    public void removeRole(Role role){
        Set<Role> roles = this.getRoles();
        if (roles.contains(role)){
            roles.remove(role);
        }
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

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
