package com.wipro.bartenders.users.api.role.update;

import com.wipro.bartenders.users.api.role.detail.RolesDetailService;
import com.wipro.bartenders.users.api.role.detail.RoleNotFoundException;
import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesUpdateService {

    @Autowired
    RolesDetailService rolesDetailService;

    @Autowired
    RoleRepository repository;

    public Role updateRole(Long id, Role newRole) {
        Role role;
        try {
            role = rolesDetailService.getByIdEager(id);
        } catch (RoleNotFoundException e){
            role = new Role();
            role.setId(id);
        }
        role.setName(newRole.getName());
        repository.save(role);
        return role;
    }
}
