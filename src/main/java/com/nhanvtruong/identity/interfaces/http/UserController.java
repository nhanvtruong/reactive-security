package com.nhanvtruong.identity.interfaces.http;

import com.nhanvtruong.identity.interfaces.dto.res.UserCreatedResponseDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserCreatedRequestDto;
import com.nhanvtruong.identity.interfaces.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userCommandHandler;

  @PostMapping("/public/users")
  public ResponseEntity<Mono<UserCreatedResponseDto>> createUser(
      @Valid @RequestBody UserCreatedRequestDto userCreatedRequestDto) {
    return new ResponseEntity<>(userCommandHandler.createUser(userCreatedRequestDto),
        HttpStatus.CREATED);
  }

}
