package com.wipro.bartenders.users.api.user.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserListControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Test
    public void listUser_callingList_returnsList() throws Exception {
        new TestSp(wac)
        .given_populated_base()
        .when_requested_list_of_users()
        .then_status_should_be_ok()
        .then_response_should_contain_list();
    }
}

class TestSp {

    MockMvc mockMvc;
    private ResultActions result;

    public TestSp(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    TestSp given_populated_base() {
        return this;
    }

    TestSp when_requested_list_of_users() throws Exception {
        this.result = mockMvc.perform(get("/users"));
        return this;
    }

    TestSp then_status_should_be_ok() throws Exception {
        this.result.andExpect(status().isOk());
        return this;
    }

    TestSp then_response_should_contain_list() throws Exception {
        this.result.andExpect(jsonPath("$.content").isArray());
        return this;
    }


}
