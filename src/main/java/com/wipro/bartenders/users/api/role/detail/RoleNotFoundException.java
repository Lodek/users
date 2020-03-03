package com.wipro.bartenders.users.api.role.detail;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(Long id){
        super(String.format("Role with id %s not found", id));
    }
}
