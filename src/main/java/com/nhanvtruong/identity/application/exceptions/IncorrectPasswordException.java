package com.nhanvtruong.identity.application.exceptions;

public class IncorrectPasswordException extends RuntimeException {

  public IncorrectPasswordException(String message) {
    super(message);
  }
}
