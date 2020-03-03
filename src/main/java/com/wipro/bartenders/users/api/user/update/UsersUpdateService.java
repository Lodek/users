package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.api.user.detail.UserNotFoundException;
import com.wipro.bartenders.users.api.user.detail.UsersDetailService;
import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersUpdateService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UsersDetailService usersDetailService;

    public User updateUser(Long id, User newUser) {
        User storedUser;
        try {
            storedUser = usersDetailService.getByIdEagerly(id);
        } catch (UserNotFoundException e) {
            storedUser = new User();
            storedUser.setId(id);
        }
        storedUser.update(newUser);
        repository.save(storedUser);
        return storedUser;
    }
}
