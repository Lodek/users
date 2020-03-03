package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


public class UsersListServiceTest {

    @Test
    public void getUsers_requestPageOfUsers_returnPageable(){
        Pageable pageable = PageRequest.of(1, 1);
        new TestSpec()
        .given_populated_base(pageable)
        .when_requested_all_users()
        .then_service_should_return_page_of_users();
    }

}

class TestSpec{

    @InjectMocks
    UsersListService service;

    @Mock
    UserRepository repository;

    @Mock
    User user;

    Pageable pageable;
    Page<User> users;

    TestSpec(){
        MockitoAnnotations.initMocks(this);
    }

    TestSpec then_service_should_return_page_of_users() {
        assertThat(this.users).isNotNull();
        then(repository).should(times(1)).findAll(pageable);
        return this;
    }

    TestSpec when_requested_all_users() {
        this.users = this.service.getUsers(pageable);
        return this;
    }


    TestSpec given_populated_base(Pageable pageable) {
        this.pageable =  pageable;
        Page<User> page = new PageImpl(Arrays.asList(this.user));
        given(this.repository.findAll(this.pageable)).willReturn(page);
        return this;
    }
}
