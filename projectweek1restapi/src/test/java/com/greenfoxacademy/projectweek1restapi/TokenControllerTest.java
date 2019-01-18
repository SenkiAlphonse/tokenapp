package com.greenfoxacademy.projectweek1restapi;

import com.greenfoxacademy.projectweek1restapi.config.JwtSecurityConfig;
import com.greenfoxacademy.projectweek1restapi.controller.TokenController;
import com.greenfoxacademy.projectweek1restapi.model.JwtUser;
import com.greenfoxacademy.projectweek1restapi.security.JwtAuthenticationEntryPoint;
import com.greenfoxacademy.projectweek1restapi.security.JwtAuthenticationProvider;
import com.greenfoxacademy.projectweek1restapi.security.JwtGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {TokenController.class, JwtSecurityConfig.class, JwtGenerator.class}
)
@AutoConfigureMockMvc(secure = false)
@TestPropertySource(locations="classpath:application-test.properties")
@EnableAutoConfiguration
public class TokenControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtAuthenticationProvider jwtAuthenticationProvider;

    /*@MockBean
    JwtGenerator jwtGenerator;*/

    @MockBean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    public void testToken() throws Exception{
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserName("BoaConstructor");
        jwtUser.setId(13);
        jwtUser.setRole("user");

        mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\": \"BoaConstructor\", \"id\": \"12345\", \"role\": \"user\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCb2FDb25zdHJ1Y3RvciIsInVzZXJJZCI6IjEyMzQ1Iiwicm9sZSI6InVzZXIifQ.CNFMwW6nAki08UujPTgKPEwQ3IBSDN6C9s1CPf_6avfFnDBofmbOLSzvhWq4dB4ZEW7FFQP3wt4DDEXCSeyZJQ"));


    }

}


