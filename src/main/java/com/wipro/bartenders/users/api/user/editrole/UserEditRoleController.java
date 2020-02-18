package com.wipro.bartenders.users.api.user.editrole;

import com.wipro.bartenders.users.api.user.common.UsersEditRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users/{id}/roles")
@RestController
public class UserEditRoleController {

    @Autowired
    UserEditRoleService service;

    @PutMapping
    public void appendRoles(@PathVariable Long id, @RequestBody UsersEditRoleDto request){
    }


    @PostMapping
    public void setRoles(@PathVariable Long id, @RequestBody UsersEditRoleDto request){
    }


    @DeleteMapping
    public void removeRoles(@PathVariable Long id, @RequestBody UsersEditRoleDto request){
    }
}
