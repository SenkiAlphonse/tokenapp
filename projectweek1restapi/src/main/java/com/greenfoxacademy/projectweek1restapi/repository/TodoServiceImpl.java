package com.greenfoxacademy.projectweek1restapi.repository;


import com.greenfoxacademy.projectweek1restapi.controller.TodoDto;
import com.greenfoxacademy.projectweek1restapi.controller.TodosListDto;
import com.greenfoxacademy.projectweek1restapi.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

  TodoRepository repository;

  @Autowired
  public TodoServiceImpl(TodoRepository repository) {
    this.repository = repository;
  }

  public void addTodo(Todo todo) {
    if (todo != null) {
      this.repository.save(todo);
    }
  }

  public List<Todo> getAll() {
    List<Todo> todos = new ArrayList<>();
    repository.findAll().forEach(todos::add);
    //com.greenfoxacademy.projectweek1restapi.repository.findAll().forEach(todo -> todos.add(todo));
    return todos;
  }

  public void deleteTodo(Todo todo) {
    this.repository.delete(todo);
  }

  public List<Todo> searchByTitle(String searchterm) {
    return this.repository.findTodosByTitleContaining(searchterm);
  }

  public Todo getTodoById(long id) {
    return repository.findById(id).orElse(null);
  }

  public void deleteTodoById(long id) {
    this.repository.deleteById(id);
  }

  @Override
  public Todo todoFromDto(TodoDto todoDto) {
    Todo newTodo = new Todo(todoDto.title);
    newTodo.setDone(todoDto.done);
    newTodo.setUrgent(todoDto.urgent);
    return newTodo;
  }

  public Todo findLastTodoAdded() {
    return repository.findAllByOrderByIdDesc().stream().findFirst().get();
  }

  @Override
  public TodosListDto todoToListDto(Todo todo) {
    TodosListDto dto = new TodosListDto();
    dto.id = todo.getId();
    dto.title = todo.getTitle();
    dto.urgent = todo.isUrgent();
    dto.done = todo.isDone();
    if (todo.getAssignee() != null) {
      dto.assignee = todo.getAssignee().getName();
    }

    return dto;
  }
}
