package com.github.developers.handler.exception;

public class UserBadRequestException extends RuntimeException {

  public UserBadRequestException(String identifier) {
    super("User with identifier [" + identifier + "] already exists.");
  }
}
