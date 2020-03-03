package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UsersListService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

}
