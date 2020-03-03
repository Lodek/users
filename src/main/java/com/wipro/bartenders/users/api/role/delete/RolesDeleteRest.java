package com.wipro.bartenders.users.api.role.delete;

import com.wipro.bartenders.users.api.role.detail.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RolesDeleteRest {

    @Autowired
    RolesDeleteService service;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Long id) throws RoleNotFoundException {
        service.deleteRole(id);
    }

}

