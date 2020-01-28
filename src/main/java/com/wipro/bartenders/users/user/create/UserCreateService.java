package com.wipro.bartenders.users.user.create;

import com.wipro.bartenders.users.user.entity.User;
import com.wipro.bartenders.users.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    @Autowired
    UserRepository userRepository;

    public String addUser(User user){
        return String.format("{\"id\":%d}", 1);
    }

    public User getUser(Long id){ return userRepository.getUsers().get(0);
    }

}
