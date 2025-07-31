package com.accounts.springbdd;

import com.accounts.controller.AccountsController;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountsController.class)
@AutoConfigureWebMvc
public class AccountStepDefination {

    private MockMvc mvc;

    ResultActions action;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @When("the client calls contact-info")
    public void theClientCallsContactInfo()  {
        try {
            action = mvc.perform(get("/api/contact-info"));
        }catch (Exception e){e.printStackTrace();}
    }
    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(Integer status) throws Exception {
        action.andExpect(status().is(status));
    }
}