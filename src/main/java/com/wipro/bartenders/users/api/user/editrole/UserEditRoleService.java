package com.wipro.bartenders.users.api.user.editrole;

import com.wipro.bartenders.users.api.role.common.RoleDuplicateException;
import com.wipro.bartenders.users.api.role.common.RoleNotFoundException;
import com.wipro.bartenders.users.api.role.detail.RoleDetailService;
import com.wipro.bartenders.users.api.user.common.UserNotFoundException;
import com.wipro.bartenders.users.api.user.detail.UserDetailService;
import com.wipro.bartenders.users.api.user.update.UserUpdateService;
import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserEditRoleService {

    @Autowired
    UserUpdateService userUpdateService;

    @Autowired
    RoleDetailService roleDetailService;

    @Autowired
    UserDetailService userDetailService;

    public User addRole(Long id, String roleName) throws UserNotFoundException, RoleNotFoundException, RoleDuplicateException {
        User user = userDetailService.getUser(id);
        Role role = roleDetailService.getRoleByName(roleName);
        user.addRole(role);
        userUpdateService.updateUser(id, user);
        return user;
    }

    public User overWriteRoles(Long id, List<String> roleNames) throws UserNotFoundException, RoleNotFoundException, RoleDuplicateException{
        User user = userDetailService.getUser(id);
        user.setRoles(new HashSet<>());
        userUpdateService.updateUser(user.getId(), user);

        for (String name : roleNames)
            this.addRole(id, name);

        return user;
    }

    public User removeRole(Long id, String roleName){
        User user = userDetailService.getUser(id);
        Role role = roleDetailService.getRoleByName(roleName);
        user.removeRole(role);
        return user;
    }

}
