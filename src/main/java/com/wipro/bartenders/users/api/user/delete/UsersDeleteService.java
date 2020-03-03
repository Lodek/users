package com.wipro.bartenders.users.api.user.delete;

import com.wipro.bartenders.users.api.user.detail.UserNotFoundException;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UsersDeleteService {

    @Autowired
    private UserRepository userRepository;

    public void deleteUser(Long id) throws UserNotFoundException{
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException(id);
        }
    }
}