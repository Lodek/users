package com.wipro.bartenders.users.api.user.update;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class UserUpdateServiceTest {

    @Test
    public void updateUser_validUserIdAndUpdatUserObject_ShouldOnlyOverwriteSetProperties(){
        Long validId = 1L;
        String newEmail = "newemail@email.com";
        new TestSpec()
        .given_an_existing_user_with_id(validId)
        .given_an_user_with_only_email(newEmail)
        .when_requested_user_update()
        .then_the_returned_users_email_should_be_updated();
    }
}

class TestSpec{

    @InjectMocks
    UserUpdateService service;

    @Mock
    UserRepository userRepository;

    Long validId;

    User existingUser;
    User newUser;
    User updatedUser;
    User returnedUser;
    private String newEmail;

    TestSpec(){
        MockitoAnnotations.initMocks(this);
    }

    TestSpec then_the_returned_users_email_should_be_updated() {
        assertThat(this.returnedUser.getEmail()).isEqualTo(newEmail);
        then(userRepository).should().findById(validId);
        return this;
    }

    TestSpec when_requested_user_update() {
        this.returnedUser = this.service.updateUser(validId, this.newUser);
        return this;
    }

    TestSpec given_an_user_with_only_email(String email) {
        this.newEmail = email;
        this.newUser = new  User();
        this.newUser.setEmail(email);
        this.updatedUser = new User(existingUser.getId(), existingUser.getUserName(),
                existingUser.getFirstName(), existingUser.getLastName(),
                existingUser.getBirthDate(), email);
        given(userRepository.save(updatedUser)).willReturn(updatedUser);
        return this;
    }

    TestSpec given_an_existing_user_with_id(long id) {
        this.validId = id;
        this.existingUser = new User(id, "userName", "firstName", "lastName",
                LocalDate.parse("2000-01-08"), "email@email.com");
        given(userRepository.findById(id)).willReturn(Optional.of(this.existingUser));
        return this;
    }

}
