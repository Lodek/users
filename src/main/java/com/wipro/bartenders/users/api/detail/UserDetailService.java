package com.wipro.bartenders.users.api.detail;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id){
        //Hard coded user 0 return as specified
        return userRepository.getUsers().get(0);
    }
}
