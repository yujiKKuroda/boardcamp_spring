package com.boardcamp.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
  @ExceptionHandler({ BadRequestException.class })
  public ResponseEntity<String> handleGameBadRequest(BadRequestException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
  @ExceptionHandler({ ConflictException.class })
  public ResponseEntity<String> handleGameConflict(ConflictException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }
}
