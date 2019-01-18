package com.greenfoxacademy.projectweek1restapi;

import com.greenfoxacademy.projectweek1restapi.config.JwtSecurityConfig;
import com.greenfoxacademy.projectweek1restapi.controller.TodosListDto;
import com.greenfoxacademy.projectweek1restapi.controller.TokenController;
import com.greenfoxacademy.projectweek1restapi.model.Todo;
import com.greenfoxacademy.projectweek1restapi.repository.TodoRepository;
import com.greenfoxacademy.projectweek1restapi.repository.TodoService;
import com.greenfoxacademy.projectweek1restapi.repository.TodoServiceImpl;
import com.greenfoxacademy.projectweek1restapi.security.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                TodoServiceImpl.class,
                TodoRepository.class
        }
)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@EnableAutoConfiguration
public class TodoServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoServiceImpl todoSvc;

    @Autowired
    TodoRepository repo;

    @MockBean


    @Test
    public void testList() throws Exception {

        todoSvc.addTodo(new Todo("macska"));
        todoSvc.addTodo(new Todo("kutya"));


        //when(this.todoSvc.getAll()).thenReturn(Arrays.asList(macska, kutya));


        mockMvc.perform(get("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorisation","Token eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYWNza2EiLCJ1c2VySWQiOiIyMyIsInJvbGUiOiJ1c2VyIn0.ckPQcpopz-cSP4wJwkVnUbSWkweHj-cCL53693jn7Xo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("macska")))
                .andExpect(jsonPath("$[1].title", is("kutya")));

    }
}
