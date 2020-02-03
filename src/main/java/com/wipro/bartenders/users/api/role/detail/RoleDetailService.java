package com.wipro.bartenders.users.api.role.detail;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDetailService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRole(Long id){
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
