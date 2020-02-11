package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.api.user.common.UserMapper;
import com.wipro.bartenders.users.api.user.common.UsersDto;
import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserUpdateRestController {

    @Autowired
    UserUpdateService userUpdateService;

    @Autowired
    UserMapper mapper;

    @PutMapping("/{id}")
    public UserUpdateResponse updateUser(@PathVariable Long id, @RequestBody UsersDto request){
        User updatedUser = mapper.fromDto(request);
        updatedUser = userUpdateService.updateUser(id, updatedUser);
        return (UserUpdateResponse) mapper.toDetailsDto(updatedUser);
    }

}
