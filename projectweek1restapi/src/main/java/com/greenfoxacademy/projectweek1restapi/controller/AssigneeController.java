package com.greenfoxacademy.projectweek1restapi.controller;

import com.greenfoxacademy.projectweek1restapi.model.Assignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.greenfoxacademy.projectweek1restapi.repository.AssigneeServiceImpl;

@Controller
public class AssigneeController {

  private AssigneeServiceImpl assigneeSvc;

  @Autowired
  public AssigneeController(AssigneeServiceImpl assigneeSvc) {
    this.assigneeSvc = assigneeSvc;
  }

  @GetMapping("/assignee")
  public String showAss(Model model) {
    model.addAttribute("assignees", assigneeSvc.getAll());
    return "assigneelist";
  }

  @GetMapping("/assignee/add")
  public String addAssForm(Model model) {
    model.addAttribute("ass", new Assignee());
    return "addassignee";
  }

  @PostMapping("/assignee/add")
  public String addNewAss(@ModelAttribute Assignee assignee) {
    assigneeSvc.addAssignee(assignee);
    return "redirect:/assignee";
  }

  @GetMapping("/{id}/delete-assignee")
  public String deleteTodo(@PathVariable long id) {
    assigneeSvc.deleteAssigneeById(id);
    return "redirect:/assignee";
  }

  @GetMapping("/{id}/edit-assignee")
  public String assEditor(@PathVariable long id, Model model) {
    model.addAttribute("ass", assigneeSvc.getAssigneeById(id));
    return "editassignee";
  }


  @PostMapping("/{id}/edit-assignee")
  public String updateAss(@ModelAttribute Assignee assignee) {
    assigneeSvc.addAssignee(assignee);

    return "redirect:/assignee";
  }
}
