package com.wipro.bartenders.users.api.user.editrole;

import com.wipro.bartenders.users.api.user.common.UsersEditRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users/{id}/roles")
@RestController
public class UserEditRoleController {

    @Autowired
    UserEditRoleService service;

    @PostMapping
    public void appendRoles(@PathVariable Long id, @RequestBody UsersEditRoleDto request){

    }

}
