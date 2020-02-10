package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.domain.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersCreateController {

    @Autowired
    UsersCreateService userCreateService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UsersCreateMapper mapper;

    @PostMapping
    public UsersCreateResponse addUser(@RequestBody  UsersCreateRequest request) {
        User user = mapper.fromDto(request);
        userCreateService.addUser(user);
        return mapper.toDto(user);
    }
}
