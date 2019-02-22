package com.greenfoxacademy.projectweek1restapi.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidTokenException extends AuthenticationException {

  public InvalidTokenException(String msg) {
    super(msg);
  }
}
