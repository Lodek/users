package com.wipro.bartenders.users.api.role.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleDeleteController {

    @Autowired
    RoleDeleteService roleDeleteService;

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id){
        roleDeleteService.deleteRole(id);
    }

}
