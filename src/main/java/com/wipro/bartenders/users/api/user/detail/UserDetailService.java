package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) throws RuntimeException{
        //Return an empty user if user is not found
        String msg = String.format("User with ID %s does not exist", id);
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(msg));
    }
}
