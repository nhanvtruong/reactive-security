package com.nhanvtruong.identity.interfaces.service;

import com.nhanvtruong.identity.interfaces.dto.res.UserLoginRequestDto;
import com.nhanvtruong.identity.interfaces.dto.rq.UserLoginResponseDto;
import reactor.core.publisher.Mono;

public interface UserAuthenticationService {

  Mono<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto);

  Mono<Void> logout(String authorizationHeader);

}
