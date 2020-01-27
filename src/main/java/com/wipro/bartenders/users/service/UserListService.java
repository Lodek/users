package com.wipro.bartenders.users.service;

import com.wipro.bartenders.users.entity.User;
import com.wipro.bartenders.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.getUsers();
    }


}
