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
        //Return an empty user if user is not found
        return userRepository.findById(id).orElse(User.emptyUser());
    }
}
