package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailService {

    @Autowired
    private UserRepository repository;

    public User getById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getByIdEagerly(Long id) throws UserNotFoundException {
        return repository.findByIdEagerly(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
