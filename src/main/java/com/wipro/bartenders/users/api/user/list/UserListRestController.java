package com.wipro.bartenders.users.api.list;

import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserListRestController {

    @Autowired
    UserListService userListService;

    @GetMapping
    public Iterable<User> listUsers(){
        return userListService.getUsers();
    }
}
