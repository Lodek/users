package com.wipro.bartenders.users.service;

import com.wipro.bartenders.users.entity.User;
import com.wipro.bartenders.users.repository.UserRepository;
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
