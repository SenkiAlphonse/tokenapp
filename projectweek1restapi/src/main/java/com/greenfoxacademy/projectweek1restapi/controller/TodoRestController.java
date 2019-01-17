package com.greenfoxacademy.projectweek1restapi.controller;

import com.greenfoxacademy.projectweek1restapi.model.Todo;
import com.greenfoxacademy.projectweek1restapi.repository.AssigneeServiceImpl;
import com.greenfoxacademy.projectweek1restapi.repository.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
public class TodoRestController {

    private TodoServiceImpl todoSvc;
    private AssigneeServiceImpl assSvc;

    @Autowired
    public TodoRestController (TodoServiceImpl todoSvc, AssigneeServiceImpl assSvc) {
        this.todoSvc = todoSvc;
        this.assSvc = assSvc;
    }

    @GetMapping("/api/todo")
    public ArrayList<TodosListDto> list(Model model, @RequestParam(required=false) boolean isActive) {

        ArrayList<Todo> todos = new ArrayList<>();
        ArrayList<TodosListDto> dtos = new ArrayList<>();

        if(isActive) {

            todoSvc.getAll()
                    .stream()
                    .filter(todo -> !todo.isDone())
                    .collect(Collectors.toList()).forEach(todos::add);
        }
        else {
            todoSvc.getAll().forEach(todos::add);
        }
        for (Todo todo : todos) {
            dtos.add(todoSvc.todoToListDto(todo));
        }
        return dtos;

    }

    @PostMapping("/api/todo/add")
    public ResponseEntity addNewTodoJson(@RequestBody TodoDto todoDto){
        if (todoDto != null) {
            todoSvc.addTodo(todoSvc.todoFromDto(todoDto));
        }
        return new ResponseEntity(todoSvc.findLastTodoAdded(), HttpStatus.OK);
    }
}
