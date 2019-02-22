package com.greenfoxacademy.projectweek1restapi.repository;

import com.greenfoxacademy.projectweek1restapi.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
  List<Todo> findTodosByTitleContaining(String term);

  List<Todo> findAllByOrderByIdDesc();

}
