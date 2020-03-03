package com.wipro.bartenders.users.api.user.role;

import com.wipro.bartenders.users.api.role.detail.RoleNotFoundException;
import com.wipro.bartenders.users.api.role.detail.RolesDetailService;
import com.wipro.bartenders.users.api.user.detail.UserNotFoundException;
import com.wipro.bartenders.users.api.user.detail.UsersDetailService;
import com.wipro.bartenders.users.api.user.update.UsersUpdateService;
import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRoleService {

    @Autowired
    UsersUpdateService usersUpdateService;

    @Autowired
    RolesDetailService rolesDetailService;

    @Autowired
    UsersDetailService usersDetailService;

    public User addRole(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException {
        User user = usersDetailService.getByIdEagerly(userId);
        Role role = rolesDetailService.getByIdEager(roleId);
        user.getRoles().add(role);
        usersUpdateService.updateUser(userId, user);
        return user;
    }

    public User removeRole(Long userId, Long roleId){
        User user = usersDetailService.getByIdEagerly(userId);
        user.getRoles().removeIf((role) -> role.getId().equals(roleId));
        usersUpdateService.updateUser(userId, user);
        return user;
    }

}
