package com.nhanvtruong.identity.interfaces.dto.res;

import com.nhanvtruong.identity.application.exceptions.enums.ErrorsEnum;

public record ErrorResponse(String errorCode, String errorMsg) {

  public ErrorResponse(ErrorsEnum errorsEnum) {
    this(errorsEnum.getErrorCode(), errorsEnum.getErrorMessage());
  }

}
