package com.nhanvtruong.identity.application.exceptions;

public class UserCreateFailedException extends RuntimeException {

  public UserCreateFailedException(String message) {
    super(message);
  }
}
