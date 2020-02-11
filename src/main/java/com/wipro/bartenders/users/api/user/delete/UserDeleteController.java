package com.wipro.bartenders.users.api.user.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserDeleteController {

    @Autowired
    private UserDeleteService userDeleteService;

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        //TODO: Delete raises excpetion when resource not found, add handler
        userDeleteService.deleteUser(id);
    }
}
