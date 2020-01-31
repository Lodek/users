package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserDetailRestController {

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userDetailService.getUser(id);
    }


}
