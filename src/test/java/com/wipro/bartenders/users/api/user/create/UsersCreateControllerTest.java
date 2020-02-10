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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersCreateControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception{
        System.out.println("Beans! :");
        System.out.println(wac.getBeanDefinitionNames());
    }

    @Test
    public void contextIsLoadedTest(){
        assertThat(wac).isNotNull();
    }

    @Test
    public void createUsers_whenAddedUsers_ReturnedObjectContainsId() throws Exception{
        new TestSp(objectMapper, wac)
        .given_user_requestDto()
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
    ResultActions resultActions;
    UsersCreateResponse expectedDto;
    UsersCreateResponse responseDto;

    TestSp(ObjectMapper objectMapper, WebApplicationContext wac) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.objectMapper = objectMapper;
    }

    TestSp when_requested_post() throws Exception{
        resultActions = mockMvc.perform(post("/users")
                          .contentType("application/json")
                          .content(objectMapper.writeValueAsString(requestDto)));
        return this;
    }

    TestSp given_user_requestDto(){
        requestDto = new UsersCreateRequest();
        requestDto.setUserName("bob.ross");
        requestDto.setFirstName("Bob");
        requestDto.setLastName("Ross");
        requestDto.setEmail("bob.ross@email.com");
        requestDto.setBirthDate("1990-12-20");
        return this;
    }

    TestSp then_response_should_be_200() throws Exception{
        resultActions.andExpect(status().isOk());
        return this;
    }

    TestSp then_response_should_have_user_id() throws Exception {
        //Using Jackson to map objects from Json and to Json
        response = resultActions.andReturn();
        String content = response.getResponse().getContentAsString();
        UsersCreateResponse responseDto = objectMapper.readValue(content, UsersCreateResponse.class);
        assertThat(responseDto.getId()).isNotNull();
        return this;
    }
}
