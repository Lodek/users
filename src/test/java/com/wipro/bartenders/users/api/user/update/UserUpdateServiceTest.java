package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.domain.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserUpdateServiceTest {

    @Test
    public void updatingUserShouldOnlyOverwritePropertiesThatAreAvailable(){
        long userId = 1;
        String newEmail = "newemail@email.com";
        TestSpec ts = new TestSpec();
        ts.givenAnExistingUserWithId(userId)
                .givenAnUserOnlyWithEmailSet(newEmail)
                .whenRequestedAnUserUpdate(userId)
                .thenTheReturnedUserEmailShouldBe(newEmail)
                .thenOtherAttributesShouldBeTheSame();
    }
}

class TestSpec{

    @Autowired
    private UserUpdateService service;

    private User existingUser;
    private User updatedUser;
    private User returnedUser;

    public TestSpec thenOtherAttributesShouldBeTheSame() {
        return this;
    }

    public TestSpec thenTheReturnedUserEmailShouldBe(String email) {
        return this;
    }

    public TestSpec whenRequestedAnUserUpdate(long id) {
        return this;
    }

    public TestSpec givenAnUserOnlyWithEmailSet(String email) {
        return this;
    }

    public TestSpec givenAnExistingUserWithId(long id) {
        return this;
    }

}
