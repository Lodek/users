package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class UserCreateServiceTest {

    @Test
    public void addUser_addingUserWithoutId_ReturnUserWithId(){
        new TestSpec()
        .given_user_without_id()
        .when_user_gets_added()
        .then_user_should_get_saved()
        .then_returned_user_should_have_any_id();
    }

}

class TestSpec{

    @InjectMocks
    UsersCreateService userCreateService;

    @Mock
    UserRepository userRepository;

    User noIdUser;
    User addedUser;

    TestSpec(){
        MockitoAnnotations.initMocks(this);
    }

    TestSpec given_user_without_id(){
        LocalDate dob = LocalDate.of(1994, 01, 01);
        noIdUser = new User("trillian", "Tricia", "McMillan", dob, "tricia42@dolphins.com");
        User returnUser = new User();
        returnUser.update(noIdUser);
        returnUser.setId(1L);
        given(userRepository.save(noIdUser)).willReturn(returnUser);
        return this;
    }

    TestSpec when_user_gets_added(){
        addedUser = userCreateService.addUser(noIdUser);
        return this;
    }

    TestSpec then_returned_user_should_have_any_id() {
        assertThat(addedUser.getId()).isNotNull();
        return this;
    }

    TestSpec then_user_should_get_saved() {
        then(userRepository).should(times(1)).save(noIdUser);
        return this;
    }

}
