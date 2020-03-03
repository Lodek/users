package com.wipro.bartenders.users.api.role.update;

import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleUpdateController {

    @Autowired
    private RolesUpdateService service;

    @Autowired
    private RolesUpdateMapper mapper;

    @PutMapping("/{id}")
    public RolesUpdateResponse updateUser(@PathVariable Long id, @RequestBody @Valid RolesUpdateRequest request){
        Role newRole = mapper.roleFromRequest(request);
        Role updatedRole = service.updateRole(id, newRole);
        return mapper.roleToResponse(updatedRole);
    }

}