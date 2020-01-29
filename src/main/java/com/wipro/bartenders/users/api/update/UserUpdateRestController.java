package com.wipro.bartenders.users.api.update;

import com.wipro.bartenders.users.domain.user.User;
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
