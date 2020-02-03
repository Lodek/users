package com.wipro.bartenders.users.domain.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    Integer permissionLevel;

    public Role() { }

    public Role(String name, int lvl) {
        this.name = name;
        this.permissionLevel = lvl;
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
