package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.api.user.create.UserCreateResponse;
import com.wipro.bartenders.users.domain.user.User;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserCreateResponse getUser(@PathVariable Long id) {
        User user = userDetailService.getUser(id);
        return dtoFromUser(user);
    }

    private UserCreateResponse dtoFromUser(User user){
        return modelMapper.map(user, UserCreateResponse.class);
    }

}
