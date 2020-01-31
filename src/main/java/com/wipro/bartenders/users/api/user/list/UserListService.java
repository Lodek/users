package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UserListService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }


}
