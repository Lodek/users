package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDetailServiceTest {

    @Mock
    UserRepository repository;

    @Test
    public void existingUserShouldBeQueryable(){
        TestSpec ts = new TestSpec();
    }
}

class TestSpec{

    @Autowired
    private UserDetailService service;

}
