package com.nhanvtruong.identity.application.exceptions;

import com.nhanvtruong.identity.application.exceptions.enums.ErrorsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {

  private final ErrorsEnum error;

}
