package com.wipro.bartenders.users.api.create;

import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserCreateRestController {

    @Autowired
    UserCreateService userCreateService;

    @PostMapping
    public String addUser(@RequestBody  User user){
        return userCreateService.addUser(user);
    }

}
