package com.wipro.bartenders.users.api.role.create;

import com.wipro.bartenders.users.api.role.common.RolesDto;
import com.wipro.bartenders.users.api.role.common.RoleMapper;
import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleCreateController {

    @Autowired
    RoleCreateService roleCreateService;

    @Autowired
    RoleMapper mapper;

    @PostMapping
    public RoleCreateResponse addRole(@RequestBody RolesDto request) {
        Role role = mapper.fromDto(request);
        roleCreateService.addRole(role);
        return (RoleCreateResponse) mapper.toDetailsDto(role);
    }

}
