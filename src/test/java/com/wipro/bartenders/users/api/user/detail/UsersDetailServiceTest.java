package com.wipro.bartenders.users.api.user.detail;

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

public class UsersDetailServiceTest {

    @Test
    public void getUser_requestValidId_ReturnAssociatedUser(){
        Long id = 10L;
        new TestSpec()
        .given_user_with_id(id)
        .when_getting_user_with_id(id)
        .then_returned_user_id_should_be(id);
    }

    @Test
    public void getUser_requestingInvalidId_ThrowsException(){
        Long invalidId = 55L;
        new TestSpec()
        .given_id_not_matching_any_users(invalidId)
        .when_requested_user_with_invalid_id(invalidId)
        .then_exception_should_have_been_raised();
    }

}

class TestSpec{

    @InjectMocks
    UsersDetailService service;

    @Mock
    UserRepository repository;

    User existingUser;
    User returnedUser;
    Exception exception;

    TestSpec(){
        MockitoAnnotations.initMocks(this);
    }

    TestSpec given_user_with_id(Long id){
        existingUser = new User(id, "SyrusBlack", "Syrus", "Amadeu",
                LocalDate.parse("2001-01-25"), "syrus.black@email.com");
        given(repository.findById(id)).willReturn(Optional.of(existingUser));
        return this;
    }

    TestSpec when_getting_user_with_id(Long id){
        returnedUser = service.getById(id);
        return this;
    }

    TestSpec then_returned_user_id_should_be(Long id){
        assertThat(returnedUser.getId()).isEqualTo(id);
        return this;
    }

    TestSpec given_id_not_matching_any_users(Long id) {
        given(repository.findById(id)).willReturn(Optional.empty());
        return this;
    }

    TestSpec when_requested_user_with_invalid_id(Long id) {
        try {
            service.getById(id);
        } catch (RuntimeException e) {
            exception = e;
        }
        return this;
    }

    TestSpec then_exception_should_have_been_raised() {
        assertThat(exception).isNotNull();
        return this;
    }
}
