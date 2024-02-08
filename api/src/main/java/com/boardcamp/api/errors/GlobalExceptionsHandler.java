package com.boardcamp.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
  @ExceptionHandler({ GameConflictException.class })
  public ResponseEntity<String> handleUserNotFound(GameConflictException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }
  
}
