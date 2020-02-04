package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UserUpdateServiceTest {

    @Test
    public void updatingUserShouldOnlyOverwritePropertiesThatAreAvailable(){
        Long userId = new Long(1);
        String newEmail = "newemail@email.com";
        new TestSpec()
        .given_an_existing_user_with_id(userId)
        .given_an_user_with_only_email(newEmail)
        .when_requested_user_update(userId)
        .then_the_returned_users_email_should_be(newEmail);
    }
}

class TestSpec{

    @InjectMocks
    UserUpdateService service;

    @Mock
    UserRepository userRepository;


    User existingUser;
    User newUser;
    User updatedUser;
    User returnedUser;

    TestSpec(){
        MockitoAnnotations.initMocks(this);
    }


    TestSpec then_the_returned_users_email_should_be(String email) {
        assertThat(this.returnedUser.getEmail()).isEqualTo(email);
        return this;
    }

    TestSpec when_requested_user_update(long id) {
        this.returnedUser = this.service.updateUser(id, this.newUser);
        return this;
    }

    TestSpec given_an_user_with_only_email(String email) {
        this.newUser = new  User();
        this.newUser.setEmail(email);
        this.updatedUser = new User(existingUser.getId(), existingUser.getUserName(),
                existingUser.getFirstName(), existingUser.getLastName(),
                existingUser.getBirthDate(), email);
        given(userRepository.save(updatedUser)).willReturn(updatedUser);
        return this;
    }

    TestSpec given_an_existing_user_with_id(long id) {
        this.existingUser = new User(id, "userName", "firstName", "lastName",
                "01-08-2000", "email@email.com");
        given(userRepository.findById(id)).willReturn(java.util.Optional.ofNullable(this.existingUser));
        return this;
    }

}
