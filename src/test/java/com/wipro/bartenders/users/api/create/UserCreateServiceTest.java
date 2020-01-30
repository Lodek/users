package com.wipro.bartenders.users.api.create;

import com.wipro.bartenders.users.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserCreateServiceTest {

    @Autowired
    UserCreateService userCreateService;

    User noId;
    String response;
    private User userOne;
    private User fetchedUser;


    @Test
    public void addingUserShouldReturnJsonWithTheirId(){
        givenUserWithoutId()
                .whenUserGetsAdded()
                .thenResponseShouldContainOnlyTheId();
    }

    private UserCreateServiceTest thenResponseShouldContainOnlyTheId() {
        assertThat(response).isEqualTo("{\"id\":1}");
        return this;
    }

    private UserCreateServiceTest givenUserWithoutId(){
        noId = new User("trillian", "Tricia", "McMillan", "1994-01-01", "tricia42@dolphins.com");
        return this;
    }

    private UserCreateServiceTest whenUserGetsAdded(){
        response = userCreateService.addUser(noId);
        return this;
    }

    @Test
    public void userGetShouldReturnUserWithId1(){
        givenUserWithId(1)
                .whenServiceFetchesUserById(1)
                .thenResponseShouldBeAUserWithId(1);
    }

    private UserCreateServiceTest thenResponseShouldBeAUserWithId(int id) {
        Assert.isTrue(fetchedUser.getId() == id);
        return this;
    }

    private UserCreateServiceTest whenServiceFetchesUserById(long id) {
        fetchedUser = userCreateService.getUser(id);
        return this;
    }

    private UserCreateServiceTest givenUserWithId(long id) {
        userOne = new User( id, "trillian", "Tricia", "McMillan", "1994-01-01", "tricia42@dolphins.com");
        return this;
    }

}
