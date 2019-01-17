package com.greenfoxacademy.projectweek1restapi.repository;

import com.greenfoxacademy.projectweek1restapi.model.Assignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AssigneeServiceImpl {

    AssigneeRepository repository;

    @Autowired
    public AssigneeServiceImpl(AssigneeRepository repository) {
        this.repository = repository;
    }

    public void addAssignee(Assignee assignee) {
        if (assignee !=null) {
            this.repository.save(assignee);
        }
    }

    public List<Assignee> getAll() {
        List<Assignee> assignees = new ArrayList<>();
        repository.findAll().forEach(assignees::add);
        //com.greenfoxacademy.projectweek1restapi.repository.findAll().forEach(todo -> todos.add(todo));
        return assignees;
    }

    public void deleteAssignee (Assignee assignee) {
        this.repository.delete(assignee);
    }

    public void deleteAssigneeById (long id){
        this.repository.deleteById(id);
    }

    public List<Assignee> searchByTitle (String searchterm) {
        return this.repository.findAssigneeByNameContaining(searchterm);
    }

    public Assignee getAssigneeById(long id) {
        return repository.findById(id).orElse(null);
    }

}
