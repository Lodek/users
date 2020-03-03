package com.wipro.bartenders.users.api.role.list;

import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolesListRest {

    @Autowired
    RolesListMapper mapper;

    @Autowired
    RolesListService service;

    @GetMapping
    public Iterable<RolesListResponse> listRoles(){
        List<Role> roles = service.listRoles();
        return roles.stream().map(mapper::roleToResponse).collect(Collectors.toList());
    }
}
