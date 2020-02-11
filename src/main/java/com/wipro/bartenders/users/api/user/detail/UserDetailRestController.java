package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.api.user.common.UserMapper;
import com.wipro.bartenders.users.api.user.common.UserNotFoundException;
import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserDetailRestController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserMapper mapper;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException e) {
        return e.getMessage();
    }

    @GetMapping("/{id}")
    public UserDetailResponse getUser(@PathVariable Long id) throws UserNotFoundException {
        User user = userDetailService.getUser(id);
        return (UserDetailResponse) mapper.toDetailsDto(user);
    }


}
