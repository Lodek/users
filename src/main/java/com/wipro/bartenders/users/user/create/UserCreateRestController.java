package com.wipro.bartenders.users.user.create;

import com.wipro.bartenders.users.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserCreateRestController {

    @Autowired
    UserCreateService userCreateService;

    @GetMapping(value="/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Long id){
        return userCreateService.getUser(id);
    }


    @PostMapping
    public String addUser(@RequestBody  User user){
        return userCreateService.addUser(user);
    }
}