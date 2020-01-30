package com.wipro.bartenders.users.api.list;

import com.wipro.bartenders.users.domain.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class UserListServiceTest {

    @Autowired
    private UserListService userListService;
    private List<User> users;


    @Test
    public void userServiceShouldReturnAllUsersStored(){
        givenNothing()
                .whenRequestedListOfUsers()
                .thenServiceShouldReturnAllUsers();
    }

    private UserListServiceTest thenServiceShouldReturnAllUsers() {
        Assert.isTrue(this.users.size() == 2);
        return this;
    }

    private UserListServiceTest whenRequestedListOfUsers() {
        this.users = userListService.getUsers();
        return this;
    }

    private UserListServiceTest givenNothing(){
        return this;
    }
}
