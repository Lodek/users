package com.wipro.bartenders.users.api.role.delete;

import com.wipro.bartenders.users.api.role.detail.RoleNotFoundException;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RolesDeleteService {

    @Autowired
    RoleRepository roleRepository;

    public void deleteRole(Long id) throws RoleNotFoundException{
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new RoleNotFoundException(id);
        }
    }

}
