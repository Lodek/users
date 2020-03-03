package com.wipro.bartenders.users.api.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/users/{id}/roles")
@RestController
public class UsersRoleRest {

    @Autowired
    private UsersRoleService service;

    @PostMapping
    public void addRole(@PathVariable Long id, @RequestBody @Valid UsersRoleRequest request){
        service.addRole(id, request.getRoleId());
    }

    @DeleteMapping
    public void removeRole(@PathVariable Long userId, @RequestBody @Valid UsersRoleRequest request){
        service.removeRole(userId, request.getRoleId());
    }

}
