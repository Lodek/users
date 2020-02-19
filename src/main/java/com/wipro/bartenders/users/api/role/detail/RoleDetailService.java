package com.wipro.bartenders.users.api.role.detail;

import com.wipro.bartenders.users.api.role.common.RoleDuplicateException;
import com.wipro.bartenders.users.api.role.common.RoleNotFoundException;
import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleDetailService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRole(Long id){
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Role getRoleByName(String name) throws RoleNotFoundException, RoleDuplicateException {
        Set<Role> roles = roleRepository.findByName(name);
        if (roles.isEmpty())
            throw new RoleNotFoundException();
        else if (roles.size() > 1)
            throw new RoleDuplicateException();
        return (Role) roles.toArray()[0];
    }
}
