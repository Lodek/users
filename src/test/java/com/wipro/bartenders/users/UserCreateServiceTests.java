package com.wipro.bartenders.users;

import com.wipro.bartenders.users.entity.User;
import com.wipro.bartenders.users.service.UserCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserCreateServiceTests {

    @Autowired
    UserCreateService userCreateService;

    User noId;
    String response;


    @Test
    public void addingUserShouldReturnJsonWithTheirId(){
        givenUserWithoutId()
                .whenUserGetsAdded()
                .thenResponseShouldContainOnlyTheId();
    }

    private UserCreateServiceTests thenResponseShouldContainOnlyTheId() {
        Assert.isTrue(response.equals("{\"id\":1}"));
        return this;
    }

    private UserCreateServiceTests givenUserWithoutId(){
        noId = new User("trillian", "Tricia", "McMillan", "1994-01-01", "tricia42@dolphins.com");
        return this;
    }

    private UserCreateServiceTests whenUserGetsAdded(){
        response = userCreateService.addUser(noId);
        return this;
    }


}
