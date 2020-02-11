package com.wipro.bartenders.users.api.role.detail;

import com.wipro.bartenders.users.api.role.common.RoleMapper;
import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleDetailController {

    @Autowired
    private RoleDetailService roleDetailService;

    @Autowired
    RoleMapper mapper;

    @GetMapping("/{id}")
    public RoleDetailResponse getRole(@PathVariable Long id){
        Role role = roleDetailService.getRole(id);
        return (RoleDetailResponse) mapper.toDetailsDto(role);
    }

}
