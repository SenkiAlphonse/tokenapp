package com.greenfoxacademy.projectweek1restapi.model;

import javax.persistence.*;

@Entity
public class Todo {


  @Id
  @GeneratedValue
  long id;

  private String title;
  private boolean urgent;
  private boolean done;

  public Assignee getAssignee() {
    return assignee;
  }

  public void setAssignee(Assignee assignee) {
    this.assignee = assignee;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assignee")
  //@OnDelete(action = OnDeleteAction.CASCADE)
  //@JsonIgnore
  private Assignee assignee;


  public Todo() {
  }

  public Todo(String title) {
    this.title = title;
    this.urgent = false;
    this.done = false;
    this.assignee = null;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setUrgent(boolean urgent) {
    this.urgent = urgent;
  }

  public void setDone(boolean done) {
    this.done = done;
  }


  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public boolean isUrgent() {
    return urgent;
  }

  public boolean isDone() {
    return done;
  }


}
