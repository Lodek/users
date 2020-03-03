package com.wipro.bartenders.users.api.role.list;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesListService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listRoles(){
        return roleRepository.findAll();
    }
}
