package com.wipro.bartenders.users.controller;

import com.wipro.bartenders.users.entity.User;
import com.wipro.bartenders.users.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserUpdateRestController {

    @Autowired
    UserUpdateService userUpdateService;

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userUpdateService.updateUser(id, user);
    }

}
