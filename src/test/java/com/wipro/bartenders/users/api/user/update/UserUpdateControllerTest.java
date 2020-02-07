package com.wipro.bartenders.users.api.user.update;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserUpdateControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Test
    public void updateUser_givenExistingUserAndNewData_ExpectUserDataToChange() throws Exception {
        Long id = 1L;
        new TestSp(wac)
        .given_user_with_id(id)
        .given_json_with_new_user_info()
        .when_requested_put_with_new_data_and_old_id()
        .then_response_should_be_ok()
        .then_response_json_should_contain_updated_data();
    }
}

class TestSp {

    Long id;

    MockMvc mockMvc;
    private String dtoJson;
    private ResultActions result;
    private String updatedEmail;

    TestSp (WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    TestSp given_user_with_id(Long id) {
        this.id =  id;
        return this;
    }

    TestSp given_json_with_new_user_info() {
        this.updatedEmail = "updated.email@email.com";
        this.dtoJson = String.format("{\"email\" : \"%s\"}", this.updatedEmail);
        return this;
    }

    TestSp when_requested_put_with_new_data_and_old_id() throws Exception {
        this.result = mockMvc.perform(put("/users/{id}", this.id.toString())
                                      .contentType("application/json")
                                      .content(this.dtoJson));
       return this;
    }

    TestSp then_response_should_be_ok() throws Exception {
        this.result.andExpect(status().isOk());
        return this;
    }

    TestSp then_response_json_should_contain_updated_data() throws Exception {
        this.result.andExpect(jsonPath("$.email").value(this.updatedEmail));
        return this;
    }


}
