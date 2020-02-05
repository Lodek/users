package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.domain.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserCreateRestController {

    @Autowired
    UserCreateService userCreateService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public UserCreateResponse addUser(@RequestBody  UserCreateRequest requestDto) {
        User user = dtoToUser(requestDto);
        userCreateService.addUser(user);
        return dtoFromUser(user);
    }

    private UserCreateResponse dtoFromUser(User user){
        return modelMapper.map(user, UserCreateResponse.class);
    }

    private User dtoToUser(UserCreateRequest requestDto){
        User user = modelMapper.map(requestDto, User.class);
        user.setBirthDate(requestDto.parseDate());
        return user;
    }
}
