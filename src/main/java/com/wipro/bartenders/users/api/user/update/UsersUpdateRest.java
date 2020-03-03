package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UsersUpdateRest {

    @Autowired
    UsersUpdateService service;

    @Autowired
    UsersUpdateMapper mapper;

    @PutMapping("/{id}")
    public UsersUpdateResponse updateUser(@PathVariable Long id, @RequestBody @Valid UsersUpdateRequest request){
        User updatedUser = mapper.userFromRequest(request);
        updatedUser = service.updateUser(id, updatedUser);
        return mapper.userToResponse(updatedUser);
    }

}
