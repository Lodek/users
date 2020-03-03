package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersCreateRest {

    @Autowired
    UsersCreateService usersCreateService;

    @Autowired
    UsersCreateMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersCreateResponse addUser(@RequestBody @Valid UsersCreateRequest request) {
        User user = mapper.userFromDto(request);
        usersCreateService.addUser(user);
        return mapper.usersToDto(user);
    }
}
