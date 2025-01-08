package com.nhanvtruong.identity.application.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorsEnum {

  INCORRECT_USER_NAME_PASSWORD("E001","Incorrect username or password"),
  // place newer enum on top
  ;
  private final String errorCode;
  private final String errorMessage;
}
