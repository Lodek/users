package com.wipro.bartenders.users.api.user.list;

import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
public class UserListServiceTest {

    @Test
    public void userServiceShouldReturnAllUsersStored(){
        new TestSpec()
        .given_mocks()
        .whenRequestedListOfUsers()
        .thenServiceShouldReturnAllUsers();
    }

}

class TestSpec{

    @InjectMocks
    UserListService service;

    @Mock
    UserRepository repository;

    @Mock
    User user;

    Iterable<User> users;

    public TestSpec(){
        MockitoAnnotations.initMocks(this);
    }

    public TestSpec thenServiceShouldReturnAllUsers() {
        assertThat(this.users).isNotNull();
        verify(repository, times(1)).findAll();
        return this;
    }

    public TestSpec whenRequestedListOfUsers() {
        this.users = service.getUsers();
        return this;
    }

    public TestSpec given_mocks() {
        when(this.repository.findAll()).thenReturn(Arrays.asList(user));
        return this;
    }
}
