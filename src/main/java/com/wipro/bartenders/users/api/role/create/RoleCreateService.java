package com.wipro.bartenders.users.api.role.create;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleCreateService {

    @Autowired
    RoleRepository roleRepository;

    public Role addRole(Role role){
        return roleRepository.save(role);
    }
}
