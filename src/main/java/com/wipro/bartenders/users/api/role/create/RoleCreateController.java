package com.wipro.bartenders.users.api.role.create;

import com.wipro.bartenders.users.domain.role.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleCreateController {

    @Autowired
    RoleCreateService roleCreateService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public RoleCreateResponse addRole(@RequestBody RoleCreateRequest requestDto) {
        Role role = dtoToUser(requestDto);
        roleCreateService.addRole(role);
        return dtoFromUser(role);
    }

    private RoleCreateResponse dtoFromUser(Role role){
        return modelMapper.map(role, RoleCreateResponse.class);
    }

    private Role dtoToUser(RoleCreateRequest requestDto){
        return modelMapper.map(requestDto, Role.class);
    }
}
