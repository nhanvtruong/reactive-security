package com.nhanvtruong.identity.infrastructure.exceptions;

public class UserCreateFailedException extends RuntimeException {

  public UserCreateFailedException(String message) {
    super(message);
  }
}
