package com.wipro.bartenders.users;

import com.wipro.bartenders.users.api.create.UserCreateService;
import com.wipro.bartenders.users.api.update.UserUpdateService;
import com.wipro.bartenders.users.domain.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserUpdateServiceTests {

    @Autowired
    private UserUpdateService userUpdateService;

    @Autowired
    //Shouldn't do this since it breaks the scope of the test application
    //Maybe Mock instead?
    private UserCreateService userCreateService;
    private User existingUser;
    private User updatedUser;
    private User returnedUser;

    @Test
    public void updatingUserShouldOnlyOverwritePropertiesThatAreAvailable(){
        long userId = 1;
        String newEmail = "newemail@email.com";
        givenAnExistingUserWithId(userId)
                .givenAnUserOnlyWithEmailSet(newEmail)
                .whenRequestedAnUserUpdate(userId)
                .thenTheReturnedUserEmailShouldBe(newEmail)
                .thenOtherAttributesShouldBeTheSame();
    }

    private UserUpdateServiceTests thenOtherAttributesShouldBeTheSame() {
        Assert.isTrue(returnedUser.getBirthDate().equals(existingUser.getBirthDate()));
        Assert.isTrue(returnedUser.getFirstName().equals(existingUser.getFirstName()));
        Assert.isTrue(returnedUser.getId().equals(existingUser.getId()));
        Assert.isTrue(returnedUser.getLastName().equals(existingUser.getLastName()));
        Assert.isTrue(returnedUser.getUserName().equals(existingUser.getUserName()));
        return this;
    }

    private UserUpdateServiceTests thenTheReturnedUserEmailShouldBe(String email) {
        Assert.isTrue(returnedUser.getEmail().equals(email));
        return this;
    }

    private UserUpdateServiceTests whenRequestedAnUserUpdate(long id) {
        this.returnedUser = userUpdateService.updateUser(id, this.updatedUser);
        return this;
    }

    private UserUpdateServiceTests givenAnUserOnlyWithEmailSet(String email) {
        this.updatedUser = new User();
        this.updatedUser.setEmail(email);
        return this;
    }

    private UserUpdateServiceTests givenAnExistingUserWithId(long id) {
        this.existingUser = userCreateService.getUser(id);
        return this;
    }
}
