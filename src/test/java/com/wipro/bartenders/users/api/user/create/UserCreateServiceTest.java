package com.wipro.bartenders.users.api.create;

import com.wipro.bartenders.users.domain.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserCreateServiceTest {

    @Test
    public void addingNewUserShouldReturnAnUserWithId(){
        TestSpec ts = new TestSpec();
        ts.givenUserWithoutId()
                .whenUserGetsAdded()
                .thenReturnedUserShouldHaveAnyId();
    }


    @Test
    public void addingUserShouldBeQueryableInRepository(){
        TestSpec ts = new TestSpec();
        ts.givenUserWithoutId()
                .whenUserGetsAdded()
                .whenAddedUserGetsQueried()
                .thenUserShouldBeFound();
    }

}

class TestSpec{

    @Autowired
    UserCreateService userCreateService;

    private User noIdUser;
    private User addedUser;

    TestSpec givenUserWithoutId(){
        this.noIdUser = new User("trillian", "Tricia", "McMillan", "1994-01-01", "tricia42@dolphins.com");
        return this;
    }

    TestSpec whenUserGetsAdded(){
        this.addedUser = userCreateService.addUser(noIdUser);
        return this;
    }

    public TestSpec thenReturnedUserShouldHaveAnyId() {
        assertThat(addedUser.getId()).isNotNull();
        return this;
    }

    public TestSpec whenAddedUserGetsQueried() {
        //this breaks encapsulation?
        //how to mock
        return this;
    }

    public TestSpec thenUserShouldBeFound() {
        //??
        return this;
    }
}
