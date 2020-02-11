package com.wipro.bartenders.users.api.user.editrole;

import com.wipro.bartenders.users.api.user.common.UserNotFoundException;
import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserEditRoleService {

    @Autowired
    UserRepository userRepository;

    public void appendRoles(Long id, List<Role> newRoles) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.appendRoles(newRoles);
        userRepository.save(user);
    }
}
