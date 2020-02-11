package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.api.user.common.UserMapper;
import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserListRestController {

    private final static int maxPageSize = 10;

    @Autowired
    UserListService userListService;

    @Autowired
    UserMapper mapper;

    @GetMapping
    public Page<UserListResponse> listUsers(@RequestParam(name="page", defaultValue="0") int pageNum,
                                            @RequestParam(name="numResults", defaultValue="1") int pageSize){
        //List user using paging, optionally receive pageSize and pageNumber
        //Silently ignore if pageSize is bigger than the  maximum allowed
        pageSize = pageSize > maxPageSize ? maxPageSize : pageSize;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<User> users = userListService.getUsers(pageable);
        return users.map((user) -> UserListResponse.class.cast(mapper.toDetailsDto(user)));
    }

}
