package com.nhanvtruong.identity.application.exceptions;

import com.nhanvtruong.identity.application.exceptions.enums.ErrorsEnum;
import com.nhanvtruong.identity.interfaces.dto.res.ApiResponse;
import com.nhanvtruong.identity.interfaces.dto.res.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandlers {

  @ExceptionHandler({IncorrectUsernamePasswordException.class})
  public ResponseEntity<ApiResponse<Void>> handleBadCredentialsException(final BaseException e) {
    var error = new ErrorResponse(e.getError());
    return new ResponseEntity<>(
        ApiResponse.<Void>builder().error(error).build(),
        HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({NoResourceFoundException.class})
  public ResponseEntity<ApiResponse<Void>> handleNotFoundException(final Exception e) {
    var error = new ErrorResponse(ErrorsEnum.NO_STATIC_RESOURCE_FOUND);
    return new ResponseEntity<>(
        ApiResponse.<Void>builder().error(error).description(e.getMessage()).build(),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({DuplicatedDataException.class})
  public ResponseEntity<ApiResponse<Void>> handleUserCreateFailedException(final BaseException e) {
    var error = new ErrorResponse(e.getError());
    return new ResponseEntity<>(
        ApiResponse.<Void>builder().error(error).build(),
        HttpStatus.CONFLICT
    );
  }

}
