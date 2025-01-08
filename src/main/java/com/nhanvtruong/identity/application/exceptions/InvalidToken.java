package com.nhanvtruong.identity.application.exceptions;

public class InvalidToken extends RuntimeException {

  public InvalidToken(String message) {
    super(message);
  }
}
