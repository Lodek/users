package com.wipro.bartenders.users.domain.role;

import com.wipro.bartenders.users.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    Integer permissionLevel;

    //TODO: Set to eager and figure out JPQL for many to many
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<User> users;

    public void appendUser(User user){
        user.appendRole(this);
    }

    public void removeUser(User user){
        user.removeRole(this);
    }

    public Role(String name, int lvl) {
        this.name = name;
        this.permissionLevel = lvl;
        this.users = new ArrayList<User>();
    }

    public Role update(Role newRole) {
        String name = newRole.getName();
        if (name != null && !name.isEmpty()){
            this.setName(name);
        }

        Integer permissionLevel = newRole.getPermissionLevel();
        if (permissionLevel != null){
            this.setPermissionLevel(permissionLevel);
        }
        return this;
    }
}
