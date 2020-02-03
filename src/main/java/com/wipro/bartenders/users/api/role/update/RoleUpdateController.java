package com.wipro.bartenders.users.api.role.update;

import com.wipro.bartenders.users.domain.role.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleUpdateController {

    @Autowired
    private RoleUpdateService roleUpdateService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{id}")
    public RoleUpdateResponse updateUser(@PathVariable Long id, @RequestBody RoleUpdateRequest request){
        Role newRole = fromDto(request);
        Role updatedRole = roleUpdateService.updateRole(id, newRole);
        return toDto(updatedRole);
    }

    private Role fromDto(RoleUpdateRequest request){
        return modelMapper.map(request, Role.class);
    }

    private RoleUpdateResponse toDto(Role role){
        return modelMapper.map(role, RoleUpdateResponse.class);
    }
}