package com.wipro.bartenders.users.api.list;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserListServiceTest {

    @Test
    public void userServiceShouldReturnAllUsersStored(){
        TestSpec ts = new TestSpec();
        ts.givenNothing()
                .whenRequestedListOfUsers()
                .thenServiceShouldReturnAllUsers();
    }

}

class TestSpec{

    @Autowired
    UserListService service;

    public TestSpec thenServiceShouldReturnAllUsers() {
        return this;
    }

    public TestSpec whenRequestedListOfUsers() {
        return this;
    }

    public TestSpec givenNothing() {
        return this;
    }
}
