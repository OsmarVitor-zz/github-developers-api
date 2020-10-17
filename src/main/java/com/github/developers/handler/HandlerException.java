package com.github.developers.handler;

import com.github.developers.handler.exception.ApiException;
import com.github.developers.handler.exception.UserBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

  private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

  @ExceptionHandler(UserBadRequestException.class)
  public ResponseEntity<ApiException> handlerBadRequestException(UserBadRequestException exception){
    return ResponseEntity.status(BAD_REQUEST).body(createResponseException(exception.getMessage(), BAD_REQUEST.value()));
  }

  private ApiException createResponseException(String message, int statusCode) {
    return new ApiException(message, statusCode);
  }

}
