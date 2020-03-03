package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersDetailRest {

    @Autowired
    private UsersDetailService service;

    @Autowired
    private UsersDetailMapper mapper;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException e) {
        return e.getMessage();
    }

    @GetMapping("/{id}")
    public UsersDetailResponse getUser(@PathVariable Long id) throws UserNotFoundException {
        User user = service.getByIdEagerly(id);
        return mapper.userToResponse(user);
    }


}
