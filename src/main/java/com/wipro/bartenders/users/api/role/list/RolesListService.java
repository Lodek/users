package com.wipro.bartenders.users.api.role.list;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolesListService {

    @Autowired
    RoleRepository roleRepository;

    public Page<Role> listRoles(Pageable pageable){
        return roleRepository.findAll(pageable);
    }
}
