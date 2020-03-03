package com.wipro.bartenders.users.api.role.detail;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesDetailService {

    @Autowired
    RoleRepository roleRepository;

    public Role getById(Long id){
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }

    public Role getByIdEager(Long id){
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }

}
