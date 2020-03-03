package com.wipro.bartenders.users.api.user.delete;

import com.wipro.bartenders.users.api.user.detail.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersDeleteRest {

    @Autowired
    private UsersDeleteService usersDeleteService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        usersDeleteService.deleteUser(id);
    }


}
