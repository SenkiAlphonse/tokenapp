package com.greenfoxacademy.projectweek1restapi.repository;

import com.greenfoxacademy.projectweek1restapi.controller.TodoDto;
import com.greenfoxacademy.projectweek1restapi.controller.TodosListDto;
import com.greenfoxacademy.projectweek1restapi.model.Todo;

public interface TodoService {
  Todo todoFromDto(TodoDto todoDto);

  TodosListDto todoToListDto(Todo todo);
}
