package com.boardcamp.api.errors;

public class GameConflictException extends RuntimeException {
  public GameConflictException(String message) {
    super(message);
  }
}
