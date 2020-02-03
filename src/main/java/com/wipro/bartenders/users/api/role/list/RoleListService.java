package com.wipro.bartenders.users.api.role.list;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleListService {

    @Autowired
    RoleRepository roleRepository;

    public Iterable<Role> listRoles(){
        return roleRepository.findAll();
    }
}
