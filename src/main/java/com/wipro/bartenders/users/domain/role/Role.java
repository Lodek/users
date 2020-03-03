package com.wipro.bartenders.users.domain.role;

import com.wipro.bartenders.users.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<User> users;

    public void appendUser(User user){
        user.appendRole(this);
    }

    public void removeUser(User user){
        user.removeRole(this);
    }

}
