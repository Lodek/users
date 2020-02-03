package com.wipro.bartenders.users.api.role.detail;

import com.wipro.bartenders.users.domain.role.Role;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;

    @GetMapping("/{id}")
    public RoleDetailResponse getRole(@PathVariable Long id){
        Role role = roleDetailService.getRole(id);
        return toDto(role);
    }

    private RoleDetailResponse toDto(Role role){
        return modelMapper.map(role, RoleDetailResponse.class);
    }
}
