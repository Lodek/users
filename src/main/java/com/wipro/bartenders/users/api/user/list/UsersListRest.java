package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class UsersListRest {

    private final static int maxPageSize = 10;

    @Autowired
    private UsersListService service;

    @Autowired
    private UsersListMapper mapper;

    @GetMapping
    public Page<UsersListResponse> listUsers(@RequestParam(name="page", defaultValue="0") int pageNum,
                                      @RequestParam(name="numResults", defaultValue="10") int pageSize){
        pageSize = pageSize > maxPageSize ? maxPageSize : pageSize;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<User> users = service.getUsers(pageable);
        return users.map(mapper::userToResponse);
    }

}
