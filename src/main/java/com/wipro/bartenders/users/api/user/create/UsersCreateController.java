package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.api.user.common.UserMapper;
import com.wipro.bartenders.users.api.user.common.UsersDetailsDto;
import com.wipro.bartenders.users.api.user.common.UsersDto;
import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersCreateController {

    @Autowired
    UsersCreateService usersCreateService;

    @Autowired
    UserMapper mapper;

    @PostMapping
    public UsersDetailsDto addUser(@RequestBody UsersDto request) {
        User user = mapper.fromDto(request);
        usersCreateService.addUser(user);
        return mapper.toDetailsDto(user);
    }
}
