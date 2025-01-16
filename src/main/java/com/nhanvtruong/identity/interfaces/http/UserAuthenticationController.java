package com.nhanvtruong.identity.interfaces.http;

import com.nhanvtruong.identity.interfaces.dto.res.ApiResponse;
import com.nhanvtruong.identity.interfaces.dto.res.UserLoginRequestDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserLoginResponseDto;
import com.nhanvtruong.identity.interfaces.service.UserAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserAuthenticationController {

  private final UserAuthenticationService userAuthenticationService;

  @PostMapping("/public/users/login")
  public Mono<ResponseEntity<ApiResponse<UserLoginResponseDto>>> login(
      @Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
    return userAuthenticationService.login(userLoginRequestDto)
        .map(res -> ResponseEntity.ok(
            ApiResponse.<UserLoginResponseDto>builder().data(res).build()
        ));
  }

  @GetMapping("/public/users/logout")
  public Mono<ResponseEntity<ApiResponse<String>>> logout(
      @RequestHeader("Authorization") String authorization) {
    return userAuthenticationService.logout(authorization)
        .then(Mono.fromCallable(() -> ResponseEntity.noContent().build()));
  }


}
