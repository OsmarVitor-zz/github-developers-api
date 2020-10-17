package com.github.developers.handler;

import com.github.developers.handler.exception.ApiException;
import com.github.developers.handler.exception.UserBadRequestException;
import com.github.developers.handler.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

  private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
  private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

  @ExceptionHandler(UserBadRequestException.class)
  public ResponseEntity<ApiException> handlerBadRequestException(UserBadRequestException exception){
    return ResponseEntity.status(BAD_REQUEST).body(createResponseException(exception.getMessage(), BAD_REQUEST.value()));
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiException> handlerNotFoundException(UserNotFoundException exception){
    return ResponseEntity.status(NOT_FOUND).body(createResponseException(exception.getMessage(), NOT_FOUND.value()));
  }

  private ApiException createResponseException(String message, int statusCode) {
    return new ApiException(message, statusCode);
  }

}
