package com.wipro.bartenders.users.api.user.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersDeleteController {

    @Autowired
    private UsersDeleteService usersDeleteService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        usersDeleteService.deleteUser(id);
    }

    @ExceptionHandler(EmptyResultDataAccessException .class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userDeleteIdNotFound(EmptyResultDataAccessException e){
        return e.getMessage();
    }
}
