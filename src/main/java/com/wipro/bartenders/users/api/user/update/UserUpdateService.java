package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(Long id, User newUser) {
        //Breaks encapsulation as well?
        //Should use UserCreateService that has a getUser method?
        User storedUser = userRepository.findById(id).orElse(User.emptyUser());
        storedUser.update(newUser);
        userRepository.save(storedUser);
        return storedUser;
    }
}
