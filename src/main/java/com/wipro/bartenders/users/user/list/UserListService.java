package com.wipro.bartenders.users.user.list;

import com.wipro.bartenders.users.user.entity.User;
import com.wipro.bartenders.users.user.repository.UserRepository;
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
