package com.wipro.bartenders.users.api.user.delete;

import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersDeleteService {

    @Autowired
    private UserRepository userRepository;

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}