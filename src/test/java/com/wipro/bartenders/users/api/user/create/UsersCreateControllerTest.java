package com.wipro.bartenders.users.api.user.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersCreateControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception{
        System.out.println("Beans!");
        for (String name : wac.getBeanDefinitionNames())
            System.out.println(name);
    }

    @Test
    public void createUsers_whenAddedUsers_returnedObjectContainsId() throws Exception{
        new TestSp(wac)
        .given_request_json()
        .when_requested_post()
        .then_response_should_be_200()
        .then_response_should_have_user_id();
    }

}

class TestSp {

    ObjectMapper objectMapper;

    MockMvc mockMvc;

    UsersCreateRequest requestDto;
    MvcResult response;
    ResultActions result;

    TestSp(WebApplicationContext wac) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.objectMapper = new ObjectMapper();
    }

    TestSp when_requested_post() throws Exception{
        result = mockMvc.perform(post("/users")
                          .contentType("application/json")
                          .content(objectMapper.writeValueAsString(requestDto)));
        return this;
    }

    TestSp given_request_json(){
        requestDto = new UsersCreateRequest();
        requestDto.setUserName("bob.ross");
        requestDto.setFirstName("Bob");
        requestDto.setLastName("Ross");
        requestDto.setEmail("bob.ross@email.com");
        requestDto.setBirthDate("1990-12-20");
        return this;
    }

    TestSp then_response_should_be_200() throws Exception{
        result.andExpect(status().isOk());
        return this;
    }

    TestSp then_response_should_have_user_id() throws Exception {
        //Using Jackson to map objects from Json and to Json
        response = result.andReturn();
        result.andExpect(jsonPath("$.id").isNotEmpty());
        return this;
    }
}
