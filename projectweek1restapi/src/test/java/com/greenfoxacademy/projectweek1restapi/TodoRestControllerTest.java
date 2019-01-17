package com.greenfoxacademy.projectweek1restapi;

import com.greenfoxacademy.projectweek1restapi.config.JwtSecurityConfig;
import com.greenfoxacademy.projectweek1restapi.model.Todo;
import com.greenfoxacademy.projectweek1restapi.repository.AssigneeServiceImpl;
import com.greenfoxacademy.projectweek1restapi.repository.TodoServiceImpl;
import org.junit.Test;
import com.greenfoxacademy.projectweek1restapi.controller.TodoRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoRestController.class)
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

        when(this.todoSvc.getAll()).thenReturn(Arrays.asList(new Todo("macska"), new Todo("kutya")));

        mockMvc.perform(get("/api/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }





}

