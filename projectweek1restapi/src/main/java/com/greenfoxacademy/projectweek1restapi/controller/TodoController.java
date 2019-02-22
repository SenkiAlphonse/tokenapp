package com.greenfoxacademy.projectweek1restapi.controller;

import com.greenfoxacademy.projectweek1restapi.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.greenfoxacademy.projectweek1restapi.repository.AssigneeServiceImpl;
import com.greenfoxacademy.projectweek1restapi.repository.TodoServiceImpl;

import java.util.stream.Collectors;

@Controller
public class TodoController {

  private TodoServiceImpl todoSvc;
  private AssigneeServiceImpl assSvc;

  @Autowired
  public TodoController(TodoServiceImpl todoSvc, AssigneeServiceImpl assSvc) {
    this.todoSvc = todoSvc;
    this.assSvc = assSvc;
  }

  @GetMapping({"/", "/list", "/todo"})
  public String list(Model model, @RequestParam(required = false) boolean isActive) {

    if (isActive) {
      model.addAttribute("todos", todoSvc.getAll()
          .stream()
          .filter(todo -> !todo.isDone())
          .collect(Collectors.toList()));
    } else {
      model.addAttribute("todos", todoSvc.getAll());
    }
    return "todolist";

  }

  @GetMapping("/todo/add")
  public String newTodo(Model model) {
    model.addAttribute("new_todo", new Todo());
    return "addtodo";
  }

  @PostMapping(value = "/todo/add",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String addTodo(@ModelAttribute(value = "new_todo") Todo newTodo) {
    newTodo.setUrgent(false);
    newTodo.setDone(false);
    this.todoSvc.addTodo(newTodo);
    return "redirect:/todo";
  }

  @GetMapping("/{id}/delete")
  public String deleteTodo(@PathVariable Long id) {
    todoSvc.deleteTodo(todoSvc.getAll().stream()
        .filter(todo -> todo.getId() == id)
        .findAny()
        .orElse(null));

    // todoSvc.deleteTodoById(id);

    return "redirect:/todo";
  }

  @GetMapping("/{id}/edit")
  public String todoEditor(@PathVariable Long id, Model model) {

    model.addAttribute("todo", todoSvc.getTodoById(id));
    model.addAttribute("assignees", assSvc.getAll());
    return "edittodo";
  }

  @PostMapping(value = "/{id}/edit")
  public String updateTodo(Long assigneeid, @ModelAttribute Todo todo) {
    if (assigneeid != null) {
      todo.setAssignee(assSvc.getAssigneeById(assigneeid));
    }
    todoSvc.addTodo(todo);
    return "redirect:/todo";
  }

  @PostMapping("/todo/search")
  public String filterTodosByTitle(String title, Model model) {
    model.addAttribute("todos", todoSvc.searchByTitle(title));
    return "todolist";
  }


}

