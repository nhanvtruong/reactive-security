package com.nhanvtruong.identity.infrastructure.exceptions;

public class InvalidToken extends RuntimeException {

  public InvalidToken(String message) {
    super(message);
  }
}
