package com.boardcamp.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
  @ExceptionHandler({ BadRequestException.class })
  public ResponseEntity<String> handleBadRequest(BadRequestException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
  @ExceptionHandler({ ConflictException.class })
  public ResponseEntity<String> handleConflict(ConflictException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }
  @ExceptionHandler({ NotFoundException.class })
  public ResponseEntity<String> handleNotFound(NotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
  @ExceptionHandler({ UnprocessableEntityException.class })
  public ResponseEntity<String> handleNotFound(UnprocessableEntityException exception) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }
}
