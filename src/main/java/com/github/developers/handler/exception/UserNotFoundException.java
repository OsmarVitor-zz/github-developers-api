package com.github.developers.handler.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String email) {
    super("User with email [" + email + "] not found.");
  }
}