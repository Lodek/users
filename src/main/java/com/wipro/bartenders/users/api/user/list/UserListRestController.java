package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.domain.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wipro.bartenders.users.util.ControllerUtil.mapIterable;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserListRestController {

    @Autowired
    UserListService userListService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<UserListResponse> listUsers(){
        Iterable<User> users = userListService.getUsers();
        return mapIterable(users, this::dtoFromUser);
    }

    private UserListResponse dtoFromUser(User user){
        return modelMapper.map(user, UserListResponse.class);
    }
}
