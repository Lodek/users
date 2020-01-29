package com.wipro.bartenders.users;

import com.wipro.bartenders.users.api.list.UserListService;
import com.wipro.bartenders.users.domain.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class UserListServiceTests {

    @Autowired
    private UserListService userListService;
    private List<User> users;


    @Test
    public void userServiceShouldReturnAllUsersStored(){
        givenNothing()
                .whenRequestedListOfUsers()
                .thenServiceShouldReturnAllUsers();
    }

    private UserListServiceTests thenServiceShouldReturnAllUsers() {
        Assert.isTrue(this.users.size() == 2);
        return this;
    }

    private UserListServiceTests whenRequestedListOfUsers() {
        this.users = userListService.getUsers();
        return this;
    }

    private UserListServiceTests givenNothing(){
        return this;
    }
}
