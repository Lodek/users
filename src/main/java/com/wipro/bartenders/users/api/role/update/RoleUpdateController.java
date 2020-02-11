package com.wipro.bartenders.users.api.role.update;

import com.wipro.bartenders.users.api.role.common.RoleMapper;
import com.wipro.bartenders.users.api.role.common.RolesDetailsDto;
import com.wipro.bartenders.users.api.role.common.RolesDto;
import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleUpdateController {

    @Autowired
    private RoleUpdateService roleUpdateService;

    @Autowired
    private RoleMapper mapper;

    @PutMapping("/{id}")
    public RolesDetailsDto updateUser(@PathVariable Long id, @RequestBody RolesDto request){
        Role newRole = mapper.fromDto(request);
        Role updatedRole = roleUpdateService.updateRole(id, newRole);
        return mapper.toDetailsDto(updatedRole);
    }

}