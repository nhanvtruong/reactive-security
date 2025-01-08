package com.nhanvtruong.identity.application.exceptions;

import com.nhanvtruong.identity.application.exceptions.enums.ErrorsEnum;

public class IncorrectUsernamePasswordException extends BaseException {
  public IncorrectUsernamePasswordException(ErrorsEnum error) {
    super(error);
  }
}
