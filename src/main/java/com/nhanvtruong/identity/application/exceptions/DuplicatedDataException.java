package com.nhanvtruong.identity.application.exceptions;

import com.nhanvtruong.identity.application.exceptions.enums.ErrorsEnum;

public class DuplicatedDataException extends BaseException {

  public DuplicatedDataException(ErrorsEnum error) {
    super(error);
  }
}
