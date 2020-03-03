package com.wipro.bartenders.users.api.user.detail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.bartenders.users.api.user.common.UsersDetailsDto;
import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Test
    public void getUser_getOnExistingUserId_returnUserWithMatchingId() throws Exception {
        Long id = 1L;
        new TestSp(wac, userRepository)
        .given_base_with_id(id)
        .when_called_getUser()
        .then_return_code_should_be_ok()
        .then_returned_user_id_should_match();

    }


    @Test
    public void getUser_getOnInvalidUserId_return404() throws Exception{
        Long id = 10L;
        new TestSp(wac, userRepository)
        .given_base_without_id(id)
        .when_called_get_user()
        .then_response_code_should_be_not_found()
        .then_response_should_have_offending_id();
    }

}

class TestSp {

    MockMvc mockMvc;
    UserRepository userRepository;

    Long id;
    private ResultActions result;
    private MvcResult response;
    private UsersDetailsDto expectedResponse;
    private User user;

    TestSp(WebApplicationContext wac, UserRepository userRepository){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.userRepository = userRepository;
    }

    public TestSp given_base_with_id(Long id) {
        this.id = id;
        String userName = "david.jon";
        String firstName = "David";
        String lastName = "Jon";
        LocalDate dob = LocalDate.of(0,1,1);
        String email = "david.jon@email.com";
        this.user = new User(id, userName, firstName, lastName, dob, email);
        expectedResponse = new UsersDetailsDto();
        expectedResponse.setUserName(userName);
        expectedResponse.setFirstName(firstName);
        expectedResponse.setLastName(lastName);
        expectedResponse.setBirthDate(dob.format(DateTimeFormatter.ISO_DATE));
        expectedResponse.setFirstName(firstName);
        expectedResponse.setEmail(email);
        expectedResponse.setId(user.getId());
        userRepository.save(user);
        //should parse json?
        return this;
    }

    public TestSp when_called_getUser() throws Exception {
        this.result = mockMvc.perform(get("/users/{id}",  this.id));
        return this;
    }

    public TestSp then_return_code_should_be_ok() throws Exception {
        result.andExpect(status().isOk());
        return this;
    }

    public TestSp then_returned_user_id_should_match() throws Exception{
        result.andExpect(jsonPath("$.id").value(this.user.getId()));
        return this;
    }

    public TestSp given_base_without_id(Long id) {
        //Calling repository to delete an User when there's a service just
        //for that feels like breaking encapsulation...
        //calling userDeleteService here seems odd because
        //it increases the ammount of dependencies for the test?
        this.id = id;
        return this;
    }

    public TestSp when_called_get_user() throws Exception {
        this.result = mockMvc.perform(get("/users/{id}",  this.id));
        return this;
    }

    public TestSp then_response_code_should_be_not_found() throws Exception {
        result.andExpect(status().isNotFound());
        return this;
    }

    public TestSp then_response_should_have_offending_id() throws Exception {
        this.result.andExpect(content().string(containsString(this.id.toString())));
        return this;
    }
}
