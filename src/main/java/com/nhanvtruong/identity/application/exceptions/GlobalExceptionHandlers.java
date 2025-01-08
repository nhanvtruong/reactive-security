package com.nhanvtruong.identity.application.exceptions;

import com.nhanvtruong.identity.interfaces.dto.res.ApiErrorResponse;
import com.nhanvtruong.identity.interfaces.dto.res.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlers {

  @ExceptionHandler({IncorrectUsernamePasswordException.class})
  public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(final BaseException e) {
    var error = new ErrorResponse(e.getError());
    return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.UNAUTHORIZED);
  }

}
