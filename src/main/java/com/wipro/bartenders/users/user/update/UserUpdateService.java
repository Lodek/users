package com.wipro.bartenders.users.user.update;

import com.wipro.bartenders.users.user.entity.User;
import com.wipro.bartenders.users.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(Long id, User newUser) {
        User storedUser = userRepository.getUsers().get(id.intValue());
        return storedUser.update(newUser);
    }
}
