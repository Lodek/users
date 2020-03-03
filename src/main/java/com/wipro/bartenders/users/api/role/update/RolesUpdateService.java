package com.wipro.bartenders.users.api.role.update;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesUpdateService {

    @Autowired
    RoleRepository roleRepository;

    public Role updateRole(Long id, Role newRole) throws RuntimeException{
        Role role = roleRepository.findById(id).orElseThrow(RuntimeException::new);
        role.update(newRole);
        roleRepository.save(role);
        return role;
    }
}
