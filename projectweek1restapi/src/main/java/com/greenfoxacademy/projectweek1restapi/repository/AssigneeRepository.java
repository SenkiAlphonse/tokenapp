package com.greenfoxacademy.projectweek1restapi.repository;

import com.greenfoxacademy.projectweek1restapi.model.Assignee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends CrudRepository<Assignee, Long> {
  List<Assignee> findAssigneeByNameContaining(String term);
}
