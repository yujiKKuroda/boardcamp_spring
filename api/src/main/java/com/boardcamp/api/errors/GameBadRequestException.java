package com.boardcamp.api.errors;

public class GameBadRequestException extends RuntimeException {
  public GameBadRequestException(String message) {
    super(message);
  }
}
