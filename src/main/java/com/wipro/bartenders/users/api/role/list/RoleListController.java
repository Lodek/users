package com.wipro.bartenders.users.api.role.list;

import com.wipro.bartenders.users.domain.role.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wipro.bartenders.users.util.ControllerUtil.mapIterable;

@RestController
@RequestMapping("/roles")
public class RoleListController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleListService roleListService;

    @GetMapping
    public List<RoleListResponse> listRoles(){
        Iterable<Role> roles = roleListService.listRoles();
        return mapIterable(roles, this::toDto);
    }


    private RoleListResponse toDto(Role role){
        return modelMapper.map(role, RoleListResponse.class);
    }
}
