package com.wipro.bartenders.users.user.list;

import com.wipro.bartenders.users.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserListRestController {

    @Autowired
    UserListService userListService;

    @GetMapping
    public List<User> listUsers(){
        return userListService.getUsers();
    }
}