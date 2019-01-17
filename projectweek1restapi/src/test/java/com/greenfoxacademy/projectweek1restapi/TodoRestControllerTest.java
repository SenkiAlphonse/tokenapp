package com.greenfoxacademy.projectweek1restapi;

import com.greenfoxacademy.projectweek1restapi.config.JwtSecurityConfig;
import com.greenfoxacademy.projectweek1restapi.controller.TodoDto;
import com.greenfoxacademy.projectweek1restapi.controller.TodosListDto;
import com.greenfoxacademy.projectweek1restapi.model.Todo;
import com.greenfoxacademy.projectweek1restapi.repository.AssigneeServiceImpl;
import com.greenfoxacademy.projectweek1restapi.repository.TodoServiceImpl;
import com.greenfoxacademy.projectweek1restapi.security.JwtAuthenticationEntryPoint;
import com.greenfoxacademy.projectweek1restapi.security.JwtAuthenticationProvider;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import com.greenfoxacademy.projectweek1restapi.controller.TodoRestController;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TodoRestController.class, secure = false)
@TestPropertySource(locations="classpath:persistence-todo.properties")
public class TodoRestControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoServiceImpl todoSvc;

    @MockBean
    private AssigneeServiceImpl assSvc;

    @Test
    public void testList() throws Exception {

        Todo macska = new Todo("macska");
        Todo kutya = new Todo("kutya");
        when(this.todoSvc.getAll()).thenReturn(Arrays.asList(macska, kutya));

        doAnswer((Answer<TodosListDto>) invocation -> {
            Todo todo = invocation.getArgument(0);
            TodosListDto dto = new TodosListDto();
            dto.title = todo.getTitle();
            dto.done = todo.isDone();
            dto.urgent = todo.isUrgent();
            return dto;
        }).when(this.todoSvc).todoToListDto(any());

        mockMvc.perform(get("/api/todo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("macska")))
                .andExpect(jsonPath("$[1].title", is("kutya")));

    }

    @Test
    public void testListActive() throws Exception {

        Todo macska = new Todo("macska");
        macska.setDone(true);
        Todo kutya = new Todo("kutya");
        when(this.todoSvc.getAll()).thenReturn(Arrays.asList(macska, kutya));

        doAnswer((Answer<TodosListDto>) invocation -> {
            Todo todo = invocation.getArgument(0);
            TodosListDto dto = new TodosListDto();
            dto.title = todo.getTitle();
            dto.done = todo.isDone();
            dto.urgent = todo.isUrgent();
            return dto;
        }).when(this.todoSvc).todoToListDto(any());

        mockMvc.perform(get("/api/todo?isActive=true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("kutya")));

    }




}

